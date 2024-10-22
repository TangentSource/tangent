package subscription.domain.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import subscription.domain.entities.SubscriptionStatus;

@RegisterForReflection
public record SubscriptionEventTypeDto(String eventType)
{
}
