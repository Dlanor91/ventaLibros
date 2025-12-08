package gm.ventas.ml.service.imp;

import gm.ventas.ml.model.VentaML;
import gm.ventas.ml.repository.VentaMLRepository;
import gm.ventas.ml.service.IVentaMLService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VentaMLService implements IVentaMLService {

    private final VentaMLRepository ventaMLRepository;

    public VentaMLService(VentaMLRepository ventaMLRepository) {
        this.ventaMLRepository = ventaMLRepository;
    }

    @Override
    public List<VentaML> listar() {
        return ventaMLRepository.findAll();
    }

    @Override
    public VentaML buscarPorId(Integer id) {
        Objects.requireNonNull(id, "El id no puede ser null");

        return ventaMLRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la venta con id: " + id)
                );
    }

    @Override
    public VentaML insertar(VentaML ventaML) {
        Objects.requireNonNull(ventaML, "La venta no puede ser null");

        VentaML nuevaVenta = new VentaML();

        nuevaVenta.setUserEmail(ventaML.getUserEmail());
        nuevaVenta.setUserPhone(ventaML.getUserPhone());
        nuevaVenta.setCantidad(ventaML.getCantidad());
        nuevaVenta.setPrecio(ventaML.getPrecio());
        nuevaVenta.setCodMoneda(ventaML.getCodMoneda());
        nuevaVenta.setIsbnLibro(ventaML.getIsbnLibro());
        nuevaVenta.setIsbnLibro(ventaML.getIsbnLibro());
        nuevaVenta.setNombreLibro(ventaML.getNombreLibro());

        return ventaMLRepository.save(nuevaVenta);
    }

    @Override
    public void eliminar(Integer id) {
        Objects.requireNonNull(id, "El id no puede ser null");

        VentaML venta = buscarPorId(id);

        ventaMLRepository.delete(venta);
    }

    @Override
    public List<VentaML> buscarVentasSinProcesar() {

        return ventaMLRepository.findByProcesadaFalse();
    }

    @Override
    public void procesarVenta(Integer id) {
        VentaML buscarVenta = buscarPorId(id);

        buscarVenta.setProcesada(true);

        ventaMLRepository.save(buscarVenta);
    }
}
