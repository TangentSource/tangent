package Event.Domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EventContextEntity extends PanacheEntity {

        String eventId;
        String key;
        String value;
}
