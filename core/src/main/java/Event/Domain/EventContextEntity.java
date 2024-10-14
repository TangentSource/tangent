package Event.Domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity(name = "tangent_event_contexts")
@AllArgsConstructor
@NoArgsConstructor
public class EventContextEntity extends PanacheEntityBase {

        @Id
        String id;
        String eventId;
        String key;
        String value;
        @CreationTimestamp
        OffsetDateTime createdAt;
}
