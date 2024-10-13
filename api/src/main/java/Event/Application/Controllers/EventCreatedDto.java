package Event.Application.Controllers;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RegisterForReflection
@AllArgsConstructor
@NoArgsConstructor
public class EventCreatedDto {

    public String id;

}
