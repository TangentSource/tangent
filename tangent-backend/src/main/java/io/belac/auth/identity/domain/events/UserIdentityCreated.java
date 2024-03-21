package io.belac.auth.identity.domain.events;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;

@RegisterForReflection
@Builder
public record UserIdentityCreated(String id) {
}
