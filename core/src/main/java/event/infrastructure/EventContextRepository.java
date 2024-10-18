package event.infrastructure;

import event.domain.EventContextEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventContextRepository implements PanacheRepositoryBase<EventContextEntity, String> {

}
