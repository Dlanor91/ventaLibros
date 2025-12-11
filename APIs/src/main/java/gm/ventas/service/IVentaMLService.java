package gm.ventas.service;

import gm.ventas.model.VentaML;

import java.util.List;

public interface IVentaMLService {
    List<VentaML> listar();

    VentaML buscarPorId(Integer id);

    VentaML insertar(VentaML ventaML);

    void eliminar(Integer id);

    List<VentaML> buscarVentasSinProcesar();

    void procesarVenta(Integer id);
}
