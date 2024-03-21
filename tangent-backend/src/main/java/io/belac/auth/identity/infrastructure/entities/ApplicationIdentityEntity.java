package io.belac.auth.identity.infrastructure.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "auth_application_identities")
public class ApplicationIdentityEntity extends PanacheEntityBase {

    @Id
    public String id;

}
