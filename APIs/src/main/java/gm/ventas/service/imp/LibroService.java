package gm.ventas.service.imp;

import gm.ventas.model.Libro;
import gm.ventas.model.VentaAmazon;
import gm.ventas.model.enums.Estados;
import gm.ventas.repository.LibroRepository;
import gm.ventas.service.ILibroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class LibroService implements ILibroService {

    private final LibroRepository libroRepository;

    private final VentaAmazonService ventaAmazonService;

    public LibroService(LibroRepository libroRepository, VentaAmazonService ventaAmazonService) {
        this.libroRepository = libroRepository;
        this.ventaAmazonService = ventaAmazonService;
    }

    @Override
    public Libro buscarPorIsbn(String isbn) {
        Objects.requireNonNull(isbn, "El isbn no puede ser null");

        if (isbn.isBlank()) {
            throw new IllegalArgumentException("El isbn no puede estar vacio");
        }

        Libro libro = libroRepository.findByIsbn(isbn);
        if (libro == null) {
            throw new EntityNotFoundException("No existe un libro con el isbn: " + isbn);
        }

        return libro;
    }

    @Scheduled(cron = "0 */1 * * * *")
    @Override
    public void descontarStock() {
        List<VentaAmazon> ventasAmazon = ventaAmazonService.buscarVentasSinProcesar();

        ventasAmazon.forEach(venta -> {
            try {
                this.procesarVenta(venta);
            } catch (RuntimeException e) {
                venta.setEstadoVenta(Estados.ERROR);
                ventaAmazonService.actualizarEstado(venta);
            }
        });
    }

    @Transactional
    public void procesarVenta(VentaAmazon venta) {

        venta.setEstadoVenta(Estados.PROCESANDO);
        ventaAmazonService.actualizarEstado(venta);

        Libro libro = libroRepository.findByIsbn(venta.getIsbnLibro());
        if (libro == null) {
            throw new EntityNotFoundException("Libro no encontrado: " + venta.getIsbnLibro());
        }

        if (libro.getStock() < venta.getCantidad()) {
            throw new IllegalStateException("Stock insuficiente para ISBN " + venta.getIsbnLibro());
        }

        libro.setStock(libro.getStock() - venta.getCantidad());
        libroRepository.save(libro);

        venta.setEstadoVenta(Estados.COMPLETADO);
        ventaAmazonService.actualizarEstado(venta);
    }
}
