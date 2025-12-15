package gm.ventas.service.imp;

import gm.ventas.model.VentaEbay;
import gm.ventas.repository.VentaEbayRepository;
import gm.ventas.service.IVentaEbayService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaEbayService implements IVentaEbayService {

    private final VentaEbayRepository ventaEbayRepository;

    public VentaEbayService(VentaEbayRepository ventaEbayRepository) {
        this.ventaEbayRepository = ventaEbayRepository;
    }

    @Override
    public VentaEbay guardar(VentaEbay venta) {
        return ventaEbayRepository.save(venta);
    }

    @Override
    public List<VentaEbay> listarTodas() {
        return ventaEbayRepository.findAll();
    }
}
