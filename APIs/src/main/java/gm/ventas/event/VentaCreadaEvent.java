package gm.ventas.event;

public record VentaCreadaEvent(
        Integer idVenta,
        String isbnLibro,
        Integer cantidad
) {
}
