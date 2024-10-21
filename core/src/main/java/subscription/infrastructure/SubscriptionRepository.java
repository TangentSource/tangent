package subscription.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import subscription.domain.entities.SubscriptionEntity;

public class SubscriptionRepository implements PanacheRepositoryBase<SubscriptionEntity, Long>{
}
