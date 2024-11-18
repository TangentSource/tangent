package io.belac.tangent.io.controllers.controllers;


import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestPath;
import io.belac.tangent.core.subscription.domain.SubscriptionService;
import io.belac.tangent.core.subscription.domain.commands.CreateSubscription;
import io.belac.tangent.core.subscription.domain.dtos.SubscriptionDto;
import io.belac.tangent.core.subscription.infrastructure.SubscriptionRepository;

@Path("/subscription")
public class SubscriptionController {

    @Inject
    SubscriptionService subscriptionService;

    @Inject
    SubscriptionRepository subscriptionRepository;


    @POST
    @Path("/create")
    @RunOnVirtualThread
    @Transactional
    public SubscriptionDto create(CreateSubscription createSubscriptionCommand) {
        return subscriptionService.create(createSubscriptionCommand);
    }

    @GET
    @RunOnVirtualThread
    @Path("/{id}")
    public SubscriptionDto get(@RestPath Long id) {
        return subscriptionRepository.findById(id).toDto();
    }


}
