package io.belac.tangent.core.subscription.infrastructure;

import io.belac.tangent.core.subscription.domain.entities.SubscriptionStatus;
import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheManager;
import io.quarkus.cache.CacheResult;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.belac.tangent.core.subscription.domain.dtos.SubscriptionDto;
import io.belac.tangent.core.subscription.domain.entities.SubscriptionEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@ApplicationScoped
public class SubscriptionRepository implements PanacheRepositoryBase<SubscriptionEntity, Long>{

        @Inject
        CacheManager cacheManager;

        public SubscriptionRepository(){
                super();

        }

        @CacheResult(cacheName = "subscriptions-all")
        public List<SubscriptionEntity> allActive(){
                return list("status", SubscriptionStatus.ACTIVE.name());
        };

        @CacheResult(cacheName = "subscriptions-event-type")
        public List<SubscriptionDto> eventTypesSubscribers(String eventType){
                List<SubscriptionEntity> list = SubscriptionEntity.find("SELECT s FROM tangent_subscriptions s JOIN s.eventTypes et where et.eventType = ?1", eventType)
                        .list();
                        return list.stream().map(SubscriptionEntity::toDto).collect(Collectors.toList());
        }

        @CacheInvalidateAll(cacheName = "subscriptions")
        @Scheduled(every = "2m")
        public void invalidateCache(){
                Optional<Cache> cache = cacheManager.getCache("subscriptions-all");
                cache.ifPresent(value -> value.invalidateAll().await().indefinitely());
                Optional<Cache> cacheEventTypes = cacheManager.getCache("subscriptions-event_type");
                cache.ifPresent(value -> value.invalidateAll().await().indefinitely());

        }

}
