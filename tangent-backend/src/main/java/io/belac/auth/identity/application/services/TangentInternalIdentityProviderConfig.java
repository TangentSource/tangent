package io.belac.auth.identity.application.services;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.security.identity.request.AuthenticationRequest;

import java.util.Map;

@ConfigGroup
public class TangentInternalIdentityProviderConfig implements AuthenticationRequest {
    @Override
    public <T> T getAttribute(String s) {
        return null;
    }

    @Override
    public void setAttribute(String s, Object o) {

    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }
    // Add fields for authentication details (like username and password)
    // Implement the getAttribute, setAttribute, and getAttributes methods
}