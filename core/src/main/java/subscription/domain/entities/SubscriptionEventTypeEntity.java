package subscription.domain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import subscription.domain.dtos.SubscriptionEventTypeDto;

@Entity(name="tangent_subscription_event_types")
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionEventTypeEntity extends PanacheEntityBase {
    @Id
    Long id;
    Long subscriptionId;
    String eventType;

    public SubscriptionEventTypeDto toDto(){
        return new SubscriptionEventTypeDto(
                this.eventType
        );
    }

}
