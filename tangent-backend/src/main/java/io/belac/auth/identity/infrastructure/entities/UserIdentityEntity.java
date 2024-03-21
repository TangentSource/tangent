package io.belac.auth.identity.infrastructure.entities;

import io.belac.tenant.infrastructure.TenantEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

@Entity(name = "auth_user_identity")
public class UserIdentityEntity extends PanacheEntityBase {

    @Id
    public String id;

    @Column(length = 100)
    public String email;
    @Column(length = 40)
    public String username;

    @Column(length = 80)
    public String name;

    @Column(length = 64)
    public String password;

    @Column(length = 64)
    public String rememberMe;

    @Column
    @CreationTimestamp
    public OffsetDateTime createdAt;

    @Column
    @UpdateTimestamp
    public OffsetDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "identity_provider_id")
    private IdpEntity idp;

    @OneToMany(mappedBy = "tenant_tenants")
    private List<TenantEntity> tenant;


}

