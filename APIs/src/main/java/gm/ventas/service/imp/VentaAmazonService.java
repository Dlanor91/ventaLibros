package gm.ventas.service.imp;

import gm.ventas.model.VentaAmazon;
import gm.ventas.repository.VentaAmazonRepository;
import gm.ventas.service.IVentaAmazonService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VentaAmazonService implements IVentaAmazonService {

    private final VentaAmazonRepository ventaAmazonRepository;

    public VentaAmazonService(VentaAmazonRepository ventaAmazonRepository) {
        this.ventaAmazonRepository = ventaAmazonRepository;
    }

    @Override
    public List<VentaAmazon> listar() {
        return ventaAmazonRepository.findAll(Sort.by("codVenta"));
    }

    @Override
    public VentaAmazon insertar(VentaAmazon ventaAmazon) {
        Objects.requireNonNull(ventaAmazon, "El objeto no puede ser null");

        VentaAmazon venta = new VentaAmazon();
        venta.setIdUsuario(ventaAmazon.getIdUsuario());
        venta.setIsbnLibro(ventaAmazon.getIsbnLibro());
        venta.setCantidad(ventaAmazon.getCantidad());

        return ventaAmazonRepository.save(venta);
    }
}
