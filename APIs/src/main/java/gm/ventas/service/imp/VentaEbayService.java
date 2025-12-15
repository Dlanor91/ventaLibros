package gm.ventas.service.imp;

import gm.ventas.event.VentaCreadaEvent;
import gm.ventas.event.VentaProducer;
import gm.ventas.model.VentaEbay;
import gm.ventas.model.enums.Estados;
import gm.ventas.repository.VentaEbayRepository;
import gm.ventas.service.IVentaEbayService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaEbayService implements IVentaEbayService {

    private final VentaEbayRepository ventaEbayRepository;
    private final VentaProducer ventaProducer;

    public VentaEbayService(VentaEbayRepository ventaEbayRepository, VentaProducer ventaProducer) {
        this.ventaEbayRepository = ventaEbayRepository;
        this.ventaProducer = ventaProducer;
    }

    @Override
    public List<VentaEbay> listarTodas() {
        return ventaEbayRepository.findAll();
    }

    @Override
    public VentaEbay buscarPorId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser null");
        }

        return ventaEbayRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No existe una venta con id " + id));
    }

    @Override
    public VentaEbay guardar(VentaEbay venta) {
        VentaEbay ventaGuardada = ventaEbayRepository.save(venta);

        //genero el topico de kafka
        VentaCreadaEvent event = new VentaCreadaEvent(
                ventaGuardada.getId(),
                ventaGuardada.getIsbnLibro(),
                ventaGuardada.getCantidad()
        );

        ventaProducer.enviarVentaCreada(event);

        return ventaGuardada;
    }

    @Override
    public VentaEbay actualizarEstadoVenta(Integer id, VentaEbay ventaEbay, Estados estado) {

        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser null");
        }

        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser null");
        }

        VentaEbay modificarVenta = buscarPorId(id);

        if (estado == Estados.COMPLETADO) {
            modificarVenta.setEstadoVenta(Estados.COMPLETADO);
        } else {
            modificarVenta.setEstadoVenta(Estados.ERROR);
        }

        return ventaEbayRepository.save(modificarVenta);
    }
}
