package io.belac.tenant.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;

@Entity(name = "tenant_tenants")
public class TenantEntity extends PanacheEntityBase {

    @Id
    String id;

    @Max(60)
    String name;
}
