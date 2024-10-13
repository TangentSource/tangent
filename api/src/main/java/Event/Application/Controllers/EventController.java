package Event.Application.Controllers;

import Common.Exceptions.ExistsException;
import Event.Domain.Commands.CreateEventCommand;
import Event.Domain.EventService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/event")
public class EventController {

    @Inject
    EventService eventService;

    @POST
    @Path("/create")
    public String create(CreateEventCommand createEventCommand) throws ExistsException {
        return eventService.create(createEventCommand);
    }

}
