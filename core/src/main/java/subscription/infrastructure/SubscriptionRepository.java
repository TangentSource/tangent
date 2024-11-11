package subscription.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import subscription.domain.dtos.SubscriptionDto;
import subscription.domain.entities.SubscriptionEntity;
import subscription.domain.entities.SubscriptionStatus;

import java.util.List;
import java.util.Map;

@ApplicationScoped
public class SubscriptionRepository implements PanacheRepositoryBase<SubscriptionEntity, Long>{

        public List<SubscriptionEntity> allActive(){
                return list("status", SubscriptionStatus.ACTIVE.name());
        };

}
