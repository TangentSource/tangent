package io.belac.tangent.core.job.domain;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.OffsetDateTime;

@RegisterForReflection
public record CreateJobRecord(
        Long id,
        Source source,
        Long sourceId,
        Boolean hasLargeData,

        String data,
        OffsetDateTime notBefore,
        OffsetDateTime byLatest,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt

) {
}
