package gm.ventas.service;

import gm.ventas.model.VentaEbay;
import gm.ventas.model.enums.Estados;

import java.util.List;

public interface IVentaEbayService {

    List<VentaEbay> listarTodas();

    VentaEbay buscarPorId(Integer id);

    VentaEbay guardar(VentaEbay venta);

    VentaEbay actualizarEstadoVenta(Integer id, Estados estado);
}
