package io.belac.tangent.core.event.domain;

import io.belac.tangent.core.event.domain.dtos.EventContextDto;
import io.belac.tangent.core.event.domain.dtos.EventDto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tangent_events")
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity extends PanacheEntityBase {

    @Id
    Long id;
    String type;
    String specVersion;    //e.g. 1.0
    String source;
    String subject;
    OffsetDateTime time;
    String dataContentType;
    String data;
    String messageGroup;
    OffsetDateTime createdAt;
    String moduleCode;
    String deduplicationId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "eventId")
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
                moduleCode,
                contexts
        );
    }


}
