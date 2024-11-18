package io.belac.tangent.core.job.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity(name="tangent_jobs")
public class JobEntity {

    @Id
    Long id;

    @Enumerated(EnumType.STRING)
    Source source;

    @Enumerated(EnumType.STRING)
    TargetType target;


}
