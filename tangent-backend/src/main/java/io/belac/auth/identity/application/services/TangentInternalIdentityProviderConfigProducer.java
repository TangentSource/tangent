package io.belac.auth.identity.application.services;

import io.quarkus.security.identity.IdentityProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class TangentInternalIdentityProviderConfigProducer {

    @Produces
    public IdentityProvider<TangentInternalIdentityProviderConfig> inhanceInternalIdentityProviderProvider() {
        return new TangentInternalIdentityProvider();
    }
}