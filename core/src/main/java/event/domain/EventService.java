package event.domain;

import common.exceptions.ExistsException;
import common.IdGenerator;
import event.domain.commands.CreateEventCommand;
import event.domain.dtos.EventContextDto;
import event.domain.dtos.EventDto;
import event.infrastructure.EventContextRepository;
import event.infrastructure.EventRepository;
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
                contextEntities
        );
        eventRepository.persist(event);
        eventContextRepository.persist(contextEntities);
        return event.toDto();
    }

}
