package gm.ventas.service;

import gm.ventas.model.VentaAmazon;

import java.util.List;

public interface IVentaAmazonService {
    List<VentaAmazon> listar();

    VentaAmazon insertar(VentaAmazon ventaAmazon);

    List<VentaAmazon> buscarVentasSinProcesar();

    void actualizarEstado(VentaAmazon venta);
}
