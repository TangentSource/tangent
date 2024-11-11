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

    @Test
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
        //Let's check to make sure that it was persisted
        var subscription = this.subscriptionService.get(subscriptionDto.id());
    }

}
