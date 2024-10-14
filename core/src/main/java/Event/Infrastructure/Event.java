package Event.Infrastructure;

import Event.Domain.EventContextEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@RegisterForReflection
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event extends PanacheEntityBase {

    @Id
    public String id;
    public String type;
    public String specVersion;
    String source;
    String subject;
    OffsetDateTime time;
    String dataContentType;
    String data;
    String messageGroup;
    String deduplicationId;
    @OneToMany
    List<EventContextEntity> contexts;


}
