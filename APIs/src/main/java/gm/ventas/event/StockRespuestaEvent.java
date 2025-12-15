package gm.ventas.event;

public record StockRespuestaEvent(
        Integer ventaId,
        String estado,
        String mensaje
) {
}
