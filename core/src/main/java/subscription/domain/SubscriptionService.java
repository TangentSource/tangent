package subscription.domain;

import common.IdGenerator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import subscription.domain.commands.CreateSubscription;
import subscription.domain.dtos.SubscriptionDto;
import subscription.domain.entities.SubscriptionEntity;
import subscription.domain.entities.SubscriptionEventTypeEntity;
import subscription.infrastructure.SubscriptionEventTypeRepository;
import subscription.infrastructure.SubscriptionRepository;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@ApplicationScoped
public class SubscriptionService {

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
        return subscriptionEntity.toDto();
    }

}
