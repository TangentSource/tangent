package io.belac.auth.identity.application.services;

import io.quarkus.security.AuthenticationFailedException;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.IdentityProvider;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TangentInternalIdentityProvider implements IdentityProvider<TangentInternalIdentityProviderConfig> {

    @Override
    public Class<TangentInternalIdentityProviderConfig> getRequestType() {
        return null;
    }

    @Override
    public Uni<SecurityIdentity> authenticate(TangentInternalIdentityProviderConfig config, AuthenticationRequestContext authenticationRequestContext) {
        // Implement your authentication logic here
        // If authentication is successful, return a Uni<SecurityIdentity> object
        // If authentication fails, return Uni.createFrom().failure(new AuthenticationFailedException())
        return Uni.createFrom().failure(new AuthenticationFailedException());
    }

    @Override
    public int priority() {
        return IdentityProvider.super.priority();
    }
}