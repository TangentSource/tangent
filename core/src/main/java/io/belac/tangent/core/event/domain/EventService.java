package io.belac.tangent.core.event.domain;

import io.belac.tangent.core.exceptions.ExistsException;
import io.belac.tangent.core.IdGenerator;
import io.belac.tangent.core.event.domain.commands.CreateEventCommand;
import io.belac.tangent.core.event.domain.dtos.EventContextDto;
import io.belac.tangent.core.event.domain.dtos.EventDto;
import io.belac.tangent.core.event.infrastructure.EventContextRepository;
import io.belac.tangent.core.event.infrastructure.EventRepository;
import io.belac.tangent.core.subscription.domain.SubscriptionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 * This domain is so simple that we will forgo rich entities for anemic ones.
 */
@ApplicationScoped
public class EventService {

    @Inject
    EventRepository eventRepository;

    @Inject
    EventContextRepository eventContextRepository;

    @Inject
    SubscriptionService subscriptionService;

    @Inject
    IdGenerator idGenerator;

    public EventDto create(CreateEventCommand createEventCommand) throws ExistsException {
        //Ensure the event ID does not exist
        if (createEventCommand.deduplicationId() != null) {
            var event = eventRepository.findByDeduplicationId(createEventCommand.deduplicationId());
            if (event.isPresent()) {
                return event.get().toDto();
            }
        }
        var id = idGenerator.getTsidLong();
        var contextEntities = new ArrayList<EventContextEntity>();
        for (EventContextDto context : createEventCommand.contexts()) {
            contextEntities.add(
                    new EventContextEntity(
                            idGenerator.getTsidLong(),
                            id,
                            context.key(),
                            context.val(),
                            OffsetDateTime.now()
                    )
            );
        }
        var event = new EventEntity(
                id,
                createEventCommand.type(),
                createEventCommand.specVersion(),
                createEventCommand.source(),
                createEventCommand.subject(),
                createEventCommand.time(),
                createEventCommand.dataContentType(),
                createEventCommand.data(),
                createEventCommand.messageGroup(),
                OffsetDateTime.now(),
                createEventCommand.deduplicationId(),
                createEventCommand.moduleCode(),
                contextEntities
        );
        eventRepository.persist(event);
        eventContextRepository.persist(contextEntities);

        //Need to create the jobs

        return event.toDto();
    }

}
