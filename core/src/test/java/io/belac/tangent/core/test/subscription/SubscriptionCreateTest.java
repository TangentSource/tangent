package io.belac.tangent.core.test.subscription;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import org.junit.jupiter.api.Test;
import io.belac.tangent.core.subscription.domain.SubscriptionService;
import io.belac.tangent.core.subscription.domain.commands.CreateSubscription;
import io.belac.tangent.core.subscription.domain.entities.SubscriptionStatus;
import io.belac.tangent.core.subscription.infrastructure.SubscriptionRepository;

import java.util.List;

@QuarkusTest
public class SubscriptionCreateTest {

    @Inject
    SubscriptionService subscriptionService;

    @Inject
    Jsonb jsonb;

    @Inject
    SubscriptionRepository subscriptionRepository;

    @Test
    @TestTransaction
    public void createSubscription() {

        var createSubscriptionCommand = new CreateSubscription(
                "testSubscriptionName",
                "test-subscription",
                "test-description",
                "test_module",
                List.of("test-event-type-1", "test-event-type-2"),
                SubscriptionStatus.ACTIVE
        );

        var subscriptionDto = this.subscriptionService.create(createSubscriptionCommand);
        var subscription = subscriptionRepository.findById(subscriptionDto.id());
        assert subscription != null;
        var dto = subscription.toDto();
        assert dto.name().equals("testSubscriptionName");
        assert dto.code().equals("test-subscription");
        assert dto.description().equals("test-description");
        assert dto.module().equals("test_module");
        assert dto.eventTypes().size() == 2;
        var subscriptions = subscriptionRepository.eventTypesSubscribers("test-event-type-1");
        assert subscriptions.size() == 1;
        var firstSubscription = subscriptions.get(0);
        assert firstSubscription.code().equals("test-subscription");
        assert firstSubscription.name().equals("testSubscriptionName");
        assert firstSubscription.description().equals("test-description");
        assert firstSubscription.module().equals("test_module");
        assert firstSubscription.eventTypes().size() == 2;
        assert firstSubscription.status().equals(SubscriptionStatus.ACTIVE);
        var noSubscription = subscriptionRepository.eventTypesSubscribers("not created");
        assert noSubscription.isEmpty();

    }

}
