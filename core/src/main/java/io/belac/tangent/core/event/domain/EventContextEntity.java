package io.belac.tangent.core.event.domain;

import io.belac.tangent.core.event.domain.dtos.EventContextDto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity(name = "tangent_event_contexts")
@AllArgsConstructor
@NoArgsConstructor
public class EventContextEntity extends PanacheEntityBase {

        @Id
        Long id;
        Long eventId;
        String key;
        String value;
        OffsetDateTime createdAt;


        public EventContextDto toDto() {
            return new EventContextDto(
                    key,
                    value
            );
        }
}
