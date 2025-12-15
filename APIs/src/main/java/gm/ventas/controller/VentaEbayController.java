package gm.ventas.controller;

import gm.ventas.model.VentaEbay;
import gm.ventas.service.imp.VentaEbayService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ventasEbay")
public class VentaEbayController {

    private static final Logger logger = LoggerFactory.getLogger(VentaEbayController.class);

    private final VentaEbayService ventaEbayService;

    public VentaEbayController(VentaEbayService ventaEbayService) {
        this.ventaEbayService = ventaEbayService;
    }

    @GetMapping
    ResponseEntity<List<VentaEbay>> getAll() {
        List<VentaEbay> listar = ventaEbayService.listarTodas();

        logger.info("Cantidad de ventas: {}", listar.size());
        return ResponseEntity.ok(listar);
    }

    @PostMapping
    ResponseEntity<VentaEbay> post(@RequestBody @Valid VentaEbay venta) {
        VentaEbay ventaGuardada = ventaEbayService.guardar(venta);

        logger.info("Venta creada con id: {}", venta.getId());
        URI location = URI.create("api/ventasEbay/" + venta.getId());

        return ResponseEntity.created(location).body(ventaGuardada);
    }
}