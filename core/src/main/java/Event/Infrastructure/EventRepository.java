package Event.Infrastructure;

import Event.Domain.EventEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class EventRepository implements PanacheRepositoryBase<EventEntity, String> {

    public Optional<EventEntity> findByDeduplicationId(String deduplicationId){
        return find("deduplicationId", deduplicationId).firstResultOptional();
    }

}
