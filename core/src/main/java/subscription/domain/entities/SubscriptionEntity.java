package subscription.domain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
    @OneToMany(mappedBy = "subscriptionId")
    List<SubscriptionEventTypeEntity> eventTypes;
    @Enumerated(EnumType.STRING)
    SubscriptionStatus status;

    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;

}
