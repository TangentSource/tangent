package subscription.domain;

import common.IdGenerator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import subscription.domain.commands.CreateSubscription;
import subscription.domain.dtos.SubscriptionDto;
import subscription.domain.dtos.SubscriptionEventTypeDto;
import subscription.domain.entities.SubscriptionEntity;
import subscription.domain.entities.SubscriptionEventTypeEntity;
import subscription.infrastructure.SubscriptionEventTypeRepository;
import subscription.infrastructure.SubscriptionRepository;

import java.time.OffsetDateTime;
import java.util.*;

@ApplicationScoped
public class SubscriptionService {

    private Map<String, Map<String, SubscriptionDto>> subscriptions; //Lookup is the event type

    private OffsetDateTime lastHydrated;

    public SubscriptionService() {
        subscriptions = new HashMap<>();
        hydrateCache();
    }

    @Inject
    IdGenerator idGenerator;

    @Inject
    SubscriptionRepository subscriptionRepository;

    @Inject
    SubscriptionEventTypeRepository subscriptionEventTypeRepository;

    public SubscriptionDto create(CreateSubscription command) {
        var id = idGenerator.getTsidLong();
        var subscriptionEventTypeEntities = new ArrayList<SubscriptionEventTypeEntity>();
        for (String eventType : command.eventTypes()) {
            subscriptionEventTypeEntities.add(
                    new SubscriptionEventTypeEntity(
                            idGenerator.getTsidLong(),
                            id,
                            eventType
                    )
            );
        }

        var subscriptionEntity = new SubscriptionEntity(
                id,
                command.name(),
                command.code(),
                command.description(),
                command.module(),
                subscriptionEventTypeEntities,
                command.status(),
                OffsetDateTime.now(),
                OffsetDateTime.now()
        );
        subscriptionRepository.persist(subscriptionEntity);
        subscriptionEventTypeRepository.persist(subscriptionEventTypeEntities);
        var dto = subscriptionEntity.toDto();
        for (SubscriptionEventTypeDto eventType: dto.eventTypes()) {
            addSubscriptionToCache(eventType.eventType(), dto);
        }
        return subscriptionEntity.toDto();
    }


    public List<SubscriptionDto> eventTypesSubscribers(String eventType){
        return subscriptions.get(eventType).values().stream().toList();
    }

    /**
     * Create the event type key if it does not exist
     */
    private void addSubscriptionToCache(String eventType, SubscriptionDto subscriptionDto){
        if (!subscriptions.containsKey(eventType)){
            subscriptions.put(eventType, new HashMap<>());
        }
        subscriptions.get(eventType).put(subscriptionDto.code(), subscriptionDto);
    }


    private void hydrateCache(){
        assert subscriptionRepository != null;
        var all = subscriptionRepository.allActive();
        for (SubscriptionEntity subscriptionEntity : all) {
            var dto = subscriptionEntity.toDto();
            for (SubscriptionEventTypeDto eventType: dto.eventTypes()) {
                addSubscriptionToCache(eventType.eventType(), dto);
            }
        }
        lastHydrated = OffsetDateTime.now();
    }

}
