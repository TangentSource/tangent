package Event.Infrastructure;

import Event.Domain.EventEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventRepository implements PanacheRepositoryBase<EventEntity, String> {


}
