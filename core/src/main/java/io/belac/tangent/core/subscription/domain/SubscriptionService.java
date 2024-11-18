package io.belac.tangent.core.subscription.domain;

import io.belac.tangent.core.IdGenerator;
import io.belac.tangent.core.subscription.domain.commands.CreateSubscription;
import io.belac.tangent.core.subscription.infrastructure.SubscriptionEventTypeRepository;
import io.belac.tangent.core.subscription.infrastructure.SubscriptionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.belac.tangent.core.subscription.domain.dtos.SubscriptionDto;
import io.belac.tangent.core.subscription.domain.entities.SubscriptionEntity;
import io.belac.tangent.core.subscription.domain.entities.SubscriptionEventTypeEntity;

import java.time.OffsetDateTime;
import java.util.*;

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
