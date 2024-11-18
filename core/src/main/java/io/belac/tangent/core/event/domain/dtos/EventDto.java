package io.belac.tangent.core.event.domain.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * CloudEvent standard compliant Event
 */
@RegisterForReflection
public record EventDto(
        Long id,
        String type,
        String specVersion,     //e.g. 1.0
        String source,
        String subject,
        OffsetDateTime time,
        String dataContentType,
        String data,
        String messageGroup,
        String deduplicationId,
        String moduleCode,      //e.g. the application or module emitting the event
        List<EventContextDto> contexts  //Allows attaching context e.g. a Transport Order has a related Sales Order

) {
}

