package subscription.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import subscription.domain.entities.SubscriptionEventTypeEntity;

@ApplicationScoped
public class SubscriptionEventTypeRepository implements PanacheRepositoryBase<SubscriptionEventTypeEntity, Long>{
}
