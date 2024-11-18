package io.belac.tangent.core.subscription.domain.dtos;

import io.belac.tangent.core.subscription.domain.entities.SubscriptionStatus;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;

@RegisterForReflection
public record SubscriptionDto(
        Long id,
        String name,
        String code,           //unique reference
        String description,
        String module,          //e.g. the application or module subscribing
        List<SubscriptionEventTypeDto> eventTypes,
        SubscriptionStatus status
) {
}
