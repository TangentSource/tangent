package Event.Domain;

import Event.Domain.Dtos.EventContextDto;
import Event.Domain.Dtos.EventDto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
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

    @OneToMany
    List<EventContextEntity> contexts;



}
