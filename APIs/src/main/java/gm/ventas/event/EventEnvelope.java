package gm.ventas.event;

import com.fasterxml.jackson.databind.JsonNode;
import gm.ventas.event.enums.EventType;

public record EventEnvelope(
        EventType eventType,
        JsonNode payload
) {
}
