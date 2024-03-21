package io.belac.auth.identity.infrastructure.entities;

import io.belac.auth.identity.domain.IdpType;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity(name = "auth_identity_providers")
public class IdpEntity extends PanacheEntityBase {

    @Id
    public String id;

    @Column
    public String identifier;

    @Column
    @Enumerated(EnumType.STRING)
    public IdpType idpType;
}
