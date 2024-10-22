package subscription.domain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="tangent_subscription_event_types")
public class SubscriptionEventTypeEntity extends PanacheEntityBase {
    @Id
    Long id;
    Long subscriptionId;
    String eventType;

}
