package gm.ventas.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import gm.ventas.event.enums.EventType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class VentaProducer {
    private final KafkaTemplate<String, EventEnvelope> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public VentaProducer(KafkaTemplate<String, EventEnvelope> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void enviarVentaCreada(VentaCreadaEvent venta) {

        EventEnvelope event = new EventEnvelope(
                EventType.VENTA_CREADA,
                objectMapper.valueToTree(venta)
        );

        kafkaTemplate.send(
                "ventas.eventos",
                venta.idVenta().toString(),
                event
        );
    }
}
