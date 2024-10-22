package subscription.domain;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import subscription.domain.commands.CreateSubscription;
import subscription.domain.dtos.SubscriptionDto;
import subscription.infrastructure.SubscriptionRepository;

@ApplicationScoped
public class SubscriptionService {

    @Inject
    SubscriptionRepository subscriptionRepository;

    public SubscriptionDto create(CreateSubscription command){

    }

}
