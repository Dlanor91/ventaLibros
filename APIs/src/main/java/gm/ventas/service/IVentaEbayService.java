package gm.ventas.service;

import gm.ventas.model.VentaEbay;

import java.util.List;

public interface IVentaEbayService {

    VentaEbay guardar(VentaEbay venta);

    List<VentaEbay> listarTodas();
}
