package gm.ventas.controller;

import gm.ventas.model.VentaAmazon;
import gm.ventas.service.imp.VentaAmazonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ventasAmazon")
public class VentaAmazonController {


    private static final Logger logger = LoggerFactory.getLogger(VentaAmazonController.class);

    private final VentaAmazonService ventaAmazonService;

    public VentaAmazonController(VentaAmazonService ventaAmazonService) {
        this.ventaAmazonService = ventaAmazonService;
    }

    @GetMapping()
    ResponseEntity<List<VentaAmazon>> getAll() {
        List<VentaAmazon> listar = ventaAmazonService.listar();

        logger.info("Cantidad de ventas en amazon: {}", listar.size());
        return ResponseEntity.ok(listar);
    }

    @PostMapping()
    ResponseEntity<VentaAmazon> post(@RequestBody @Valid VentaAmazon ventaAmazon) {
        VentaAmazon venta = ventaAmazonService.insertar(ventaAmazon);

        logger.info("Venta por amazon creada con cod de venta: {}", venta.getCodVenta());
        URI location = URI.create("api/ventasAmazon/" + venta.getCodVenta());

        return ResponseEntity.created(location).body(venta);
    }

}
