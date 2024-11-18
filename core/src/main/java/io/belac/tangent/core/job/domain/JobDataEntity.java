package io.belac.tangent.core.job.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="tangent_job_data")
public class JobDataEntity {

    @Id
    Long id;

    String data;

}
