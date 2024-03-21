package io.belac.auth.identity.domain.commands;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record CreateUserIdentity(String email, String password, String name) {
}
