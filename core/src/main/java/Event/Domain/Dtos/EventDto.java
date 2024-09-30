package Event.Domain.Dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.OffsetDateTime;
import java.util.List;

@RegisterForReflection
public record EventDto(
        String id,
        String type,
        String specVersion,     //e.g.
        String source,
        String subject,
        OffsetDateTime time,
        String dataContentType,
        String data,
        String messageGroup,
        List<EventContextDto> contexts//Allows attaching context e.g. a Transport Order has a related Sales Order

) {
}

