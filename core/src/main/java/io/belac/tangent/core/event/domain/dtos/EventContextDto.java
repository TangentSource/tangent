package io.belac.tangent.core.event.domain.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record EventContextDto(String key, String val) {
}
