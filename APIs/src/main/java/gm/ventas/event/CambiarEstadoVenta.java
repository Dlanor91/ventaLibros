package gm.ventas.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import gm.ventas.event.enums.EventType;
import gm.ventas.model.enums.Estados;
import gm.ventas.service.imp.VentaEbayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CambiarEstadoVenta {

    private final VentaEbayService ventaEbayService;
    private final ObjectMapper objectMapper;

    public CambiarEstadoVenta(VentaEbayService ventaEbayService, ObjectMapper objectMapper) {
        this.ventaEbayService = ventaEbayService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "stock.respuesta")
    public void cambiarEstado(ConsumerRecord<String, String> record) {
        try {
            EventEnvelope event = objectMapper.readValue(record.value(), EventEnvelope.class);
            StockRespuestaEvent respuesta = objectMapper.convertValue(event.payload(), StockRespuestaEvent.class);

            switch (event.eventType()) {

                case EventType.STOCK_COMPLETADO -> {
                    ventaEbayService.actualizarEstadoVenta(respuesta.ventaId(), Estados.COMPLETADO);
                    log.info("Venta {} completada correctamente", respuesta.ventaId());
                }

                case EventType.ERROR_STOCK -> {
                    ventaEbayService.actualizarEstadoVenta(respuesta.ventaId(), Estados.ERROR);
                    log.warn("Error en venta {}: {}", respuesta.ventaId(), respuesta.mensaje());
                }

                default -> log.warn(
                        "Evento de stock no reconocido: {}",
                        event.eventType()
                );
            }
        } catch (Exception e) {
            log.error("Error procesando respuesta de stock: {}", record.value(), e);
        }
    }
}
