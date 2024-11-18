package io.belac.tangent.core.subscription.domain.entities;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public enum SubscriptionStatus {

    ACTIVE("active"),
    INACTIVE("inactive"),
    PENDING("pending");

    public final String label;

    // Constructor to initialize the enum with a string value
    SubscriptionStatus(String label) {
        this.label = label;
    }

}
