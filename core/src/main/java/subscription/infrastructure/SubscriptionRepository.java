package subscription.infrastructure;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import subscription.domain.dtos.SubscriptionDto;
import subscription.domain.entities.SubscriptionEntity;
import subscription.domain.entities.SubscriptionStatus;

import java.util.List;
import java.util.concurrent.TimeUnit;


@ApplicationScoped
public class SubscriptionRepository implements PanacheRepositoryBase<SubscriptionEntity, Long>{
        Cache<String, SubscriptionEntity> cache;

        public SubscriptionRepository(){
                super();
                cache = Caffeine.newBuilder()
                        .expireAfterWrite(2, TimeUnit.MINUTES)
                        .maximumSize(1000)
                        .build();
        }

        public List<SubscriptionEntity> allActive(){
                return cache.getIfPresent()
                var out = list("status", SubscriptionStatus.ACTIVE.name());
                return list("status", SubscriptionStatus.ACTIVE.name());
        };

        public List<SubscriptionDto> eventTypesSubscribers(String eventType){
                return subscriptions.get(eventType).values().stream().toList();
        }

}
