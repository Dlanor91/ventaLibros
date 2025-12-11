package gm.ventas.service;

import gm.ventas.model.VentaML;

import java.util.List;

public interface IVentaMLService {
    public List<VentaML> listar();

    public VentaML buscarPorId(Integer id);

    public VentaML insertar(VentaML ventaML);

    public void eliminar(Integer id);

    public List<VentaML> buscarVentasSinProcesar();

    public void procesarVenta(Integer id);
}
