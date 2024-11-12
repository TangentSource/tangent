package subscription;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import subscription.domain.SubscriptionService;
import subscription.domain.commands.CreateSubscription;
import subscription.domain.entities.SubscriptionStatus;
import subscription.infrastructure.SubscriptionRepository;

import java.util.List;

@QuarkusTest
public class SubscriptionCreateTest {

    @Inject
    SubscriptionService subscriptionService;

    @Inject
    SubscriptionRepository subscriptionRepository;

    //@Test
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
        assert subscriptions.getFirst().code().equals("test-subscription");
        assert subscriptions.getFirst().name().equals("testSubscriptionName");
        assert subscriptions.getFirst().description().equals("test-description");
        assert subscriptions.getFirst().module().equals("test_module");
        assert subscriptions.getFirst().eventTypes().size() == 2;
        assert subscriptions.getFirst().status().equals(SubscriptionStatus.ACTIVE);
        var noSubscription = subscriptionRepository.eventTypesSubscribers("not created");
        assert noSubscription.isEmpty();


    }

}
