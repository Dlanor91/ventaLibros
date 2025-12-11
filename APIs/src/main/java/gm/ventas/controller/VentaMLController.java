package gm.ventas.controller;

import gm.ventas.model.VentaML;
import gm.ventas.service.imp.VentaMLService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaMLController {

    private static final Logger logger = LoggerFactory.getLogger(VentaMLController.class);

    private final VentaMLService ventaMLService;

    public VentaMLController(VentaMLService ventaMLService) {
        this.ventaMLService = ventaMLService;
    }

    @GetMapping
    ResponseEntity<List<VentaML>> getAll() {
        List<VentaML> listar = ventaMLService.listar();

        logger.info("Cantidad de ventas: {}", listar.size());
        return ResponseEntity.ok(listar);
    }

    @GetMapping("{id}")
    ResponseEntity<VentaML> getById(@PathVariable Integer id) {
        VentaML ventaML = ventaMLService.buscarPorId(id);

        logger.info("Venta encontrada con id: {}", id);
        return ResponseEntity.ok(ventaML);
    }

    @PostMapping()
    ResponseEntity<VentaML> post(@RequestBody @Valid VentaML ventaML) {
        VentaML venta = ventaMLService.insertar(ventaML);

        logger.info("Venta creada con id: {}", venta.getId());
        URI location = URI.create("api/venta/" + venta.getId());

        return ResponseEntity.created(location).body(venta);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id) {
        ventaMLService.eliminar(id);

        logger.info("Venta eliminada con id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sinProcesar")
    ResponseEntity<List<VentaML>> listarSinProcesar() {
        List<VentaML> listar = ventaMLService.buscarVentasSinProcesar();

        logger.info("Cantidad de ventas sin procesar: {}", listar.size());
        return ResponseEntity.ok(listar);
    }

    @PostMapping("/{id}/procesar")
    public void procesarVenta(@PathVariable Integer id) {
        ventaMLService.procesarVenta(id);
    }
}
