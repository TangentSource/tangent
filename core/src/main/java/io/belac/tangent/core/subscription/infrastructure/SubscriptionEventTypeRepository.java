package io.belac.tangent.core.subscription.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import io.belac.tangent.core.subscription.domain.entities.SubscriptionEventTypeEntity;

@ApplicationScoped
public class SubscriptionEventTypeRepository implements PanacheRepositoryBase<SubscriptionEventTypeEntity, Long>{
}
