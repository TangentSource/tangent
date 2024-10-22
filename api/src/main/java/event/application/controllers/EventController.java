package event.application.controllers;

import common.exceptions.ExistsException;
import common.IdGenerator;
import event.domain.commands.CreateEventCommand;
import event.domain.dtos.EventContextDto;
import event.domain.dtos.EventDto;
import event.domain.EventService;
import event.infrastructure.EventRepository;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestPath;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@Path("/event")
public class EventController {

    @Inject
    EventService eventService;

    @Inject
    IdGenerator idGenerator;
    @Inject
    EventRepository eventRepository;

    @POST
    @Path("/create")
    @RunOnVirtualThread
    @Transactional
    public EventDto create(CreateEventCommand createEventCommand) throws ExistsException {
        return eventService.create(createEventCommand);
    }


    @GET
    @RunOnVirtualThread
    @Path("/{id}")
    public EventDto get(@RestPath Long id) {
        var event = eventRepository.findById(id);
        return event.toDto();
    }

    @GET
    @Path("/create-id")
    @RunOnVirtualThread
    public Long createId() {
        return idGenerator.getTsidLong();
    }

    @GET
    @Path("/generate-create-event-command")
    @RunOnVirtualThread
    public CreateEventCommand generateCreateEventCommand() {
        var context1 = new EventContextDto("key1", "val1");
        var context2 = new EventContextDto("key2", "val2");
        var contexts = new ArrayList<EventContextDto>();
        contexts.add(context1);
        contexts.add(context2);

        return new CreateEventCommand(
                "test-event",
                "1.0",
                "test-source",
                "test-subject",
                OffsetDateTime.now(),
                "application/json",
                "{\"test\":\"data\"}",
                "test-message-group",
                "test-deduplication-id",
                contexts
        );
    }

}
