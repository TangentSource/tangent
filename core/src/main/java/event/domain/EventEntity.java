package event.domain;

import event.domain.dtos.EventContextDto;
import event.domain.dtos.EventDto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tangent_events")
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity extends PanacheEntityBase {

    @Id
    String id;
    String type;
    String specVersion;    //e.g. 1.0
    String source;
    String subject;
    OffsetDateTime time;
    String dataContentType;
    String data;
    String messageGroup;
    OffsetDateTime createdAt;
    String deduplicationId;

    @OneToMany(mappedBy = "eventId")
    List<EventContextEntity> contexts;


    public EventDto toDto() {
        var contexts = new ArrayList<EventContextDto>();
        for (EventContextEntity context : this.contexts) {
            contexts.add(context.toDto());
        }
        return new EventDto(
                id,
                type,
                specVersion,
                source,
                subject,
                time,
                dataContentType,
                data,
                messageGroup,
                deduplicationId,
                contexts
        );
    }


}
