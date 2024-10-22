package subscription.domain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import subscription.domain.dtos.SubscriptionDto;

import java.time.OffsetDateTime;
import java.util.List;

@Entity(name="tangent_subscriptions")
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionEntity extends PanacheEntityBase {
    @Id
    Long id;
    String name;
    String code;            //unique reference
    String description;
    String module;          //e.g. the application or module subscribing
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "subscriptionId")
    List<SubscriptionEventTypeEntity> eventTypes;
    @Enumerated(EnumType.STRING)
    SubscriptionStatus status;

    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;

    public SubscriptionDto toDto(){
        return new SubscriptionDto(
                this.id,
                this.name,
                this.code,
                this.description,
                this.module,
                this.eventTypes.stream().map(SubscriptionEventTypeEntity::toDto).toList(),
                this.status
        );
    }

}
