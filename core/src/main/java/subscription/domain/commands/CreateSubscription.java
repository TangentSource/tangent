package subscription.domain.commands;

import io.quarkus.runtime.annotations.RegisterForReflection;
import subscription.domain.entities.SubscriptionEventTypeEntity;
import subscription.domain.entities.SubscriptionStatus;

import java.util.List;

@RegisterForReflection
public record CreateSubscription(
        String name,
        String code,            //unique reference
        String description,
        String module,          //e.g. the application or module subscribing
        List<SubscriptionEventTypeEntity> eventTypes,
        SubscriptionStatus status
) {
}
