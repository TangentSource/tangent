package io.belac.tangent.core.subscription.domain.entities;

import io.belac.tangent.core.subscription.domain.dtos.SubscriptionEventTypeDto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
