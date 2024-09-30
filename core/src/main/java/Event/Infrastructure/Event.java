package Event.Infrastructure;

import Event.Domain.Dtos.EventContextDto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.OffsetDateTime;
import java.util.List;

@RegisterForReflection
public class Event extends PanacheEntityBase {

    public String id;
    public String type;
    public String specVersion;
    String source;
    String subject;
    OffsetDateTime time;
    String dataContentType;
    String data;
    String messageGroup;
    List<EventContextDto> contexts;
    

}
