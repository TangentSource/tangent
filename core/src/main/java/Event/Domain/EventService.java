package Event.Domain;

import Common.Exceptions.ExistsException;
import Common.IdGenerator;
import Event.Domain.Commands.CreateEventCommand;
import Event.Domain.Dtos.EventContextDto;
import Event.Infrastructure.EventRepository;
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
    IdGenerator idGenerator;

    public String create(CreateEventCommand createEventCommand) throws ExistsException {
        //Ensure the event ID does not exist
        if (createEventCommand.deduplicationId() != null) {
            var event = eventRepository.findByDeduplicationId(createEventCommand.deduplicationId());
                if (event.isPresent()) {
                    return event.get().id;
                }
            }
        var id = idGenerator.getTsidString();
        var contextEntities = new ArrayList<EventContextEntity>();
        for (EventContextDto context: createEventCommand.contexts()){
            contextEntities.add(
                    new EventContextEntity
                            (idGenerator.getTsidString(),
                                    id,
                                    context.key(),
                                    context.val(),
                                    OffsetDateTime.now()
                            )
            );
        }

        eventRepository.persist(
                new EventEntity(
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
                        contextEntities
                )
        );
        return id;
    }

}
