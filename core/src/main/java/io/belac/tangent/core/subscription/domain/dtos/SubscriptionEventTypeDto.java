package io.belac.tangent.core.subscription.domain.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record SubscriptionEventTypeDto(String eventType)
{
}
