package Event.Domain;

import Common.Exceptions.ExistsException;
import Event.Domain.Dtos.EventDto;
import Event.Infrastructure.EventRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
/**
 * This domain is so simple that we will forgo frich entities for anemic ones.
 */
@ApplicationScoped
public class EventService {

    @Inject
    EventRepository eventRepository;

    public void create(EventDto eventDto) throws ExistsException {
        //Ensure the event ID does not exist
        if (eventRepository.findByIdOptional(eventDto.id()).isPresent()){
            throw new ExistsException(EventEntity.class.getCanonicalName(), eventDto.id());
        }
        eventRepository.persist(new EventEntity());
    }

}
