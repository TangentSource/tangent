package io.belac.tangent.core.exceptions;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ExistsException extends Exception {

    public ExistsException(String id, String entityType) {
        super("Entity with ID " + id + " of TYPE " + entityType + " exists.");
    }

}
