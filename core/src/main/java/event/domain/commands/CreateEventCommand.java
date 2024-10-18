package event.domain.commands;

import event.domain.dtos.EventContextDto;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.OffsetDateTime;
import java.util.List;

@RegisterForReflection
public record CreateEventCommand(
    String type,
    String specVersion,     //e.g. 1.0
    String source,
    String subject,
    OffsetDateTime time,
    String dataContentType,
    String data,
    String messageGroup,
    String deduplicationId,
    List<EventContextDto> contexts
) {
}
