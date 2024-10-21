package subscription.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import subscription.domain.entities.SubscriptionEventTypeEntity;

public class SubscriptionEventTypeRepository implements PanacheRepositoryBase<SubscriptionEventTypeEntity, Long>{
}
