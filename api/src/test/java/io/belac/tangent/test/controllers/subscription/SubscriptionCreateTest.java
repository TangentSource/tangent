package io.belac.tangent.test.controllers.subscription;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import io.belac.tangent.core.subscription.domain.commands.CreateSubscription;
import io.belac.tangent.core.subscription.domain.dtos.SubscriptionDto;
import io.belac.tangent.core.subscription.domain.entities.SubscriptionStatus;
import io.belac.tangent.core.subscription.infrastructure.SubscriptionRepository;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class SubscriptionCreateTest {

    @Inject
    ObjectMapper objectMapper;

    @Inject
    SubscriptionRepository subscriptionRepository;

    @Test
    public void testCreate() throws JsonProcessingException {
        var eventTypes = new ArrayList<String>();
        eventTypes.add("test-event-type-1");
        eventTypes.add("test-event-type-2");
        var command = new CreateSubscription(
                "testSubscriptionName",
                "test-subscription",
                "test-description",
                "Test Module",
                eventTypes,
                SubscriptionStatus.ACTIVE
        );

        var commandJson = objectMapper.writeValueAsString(command);
        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .body(commandJson)
                .post("/subscription/create")
                .then()
                .statusCode(200)
                .extract()
                .response();
        var dto = response.as(SubscriptionDto.class);
        assertTrue(dto.id() > 0, "ID should be present");

        //lets get it back
        response = given()
                .when()
                .get("/subscription/" + dto.id())
                .then()
                .statusCode(200)
                .extract()
                .response();
        var subscription = response.as(SubscriptionDto.class);
        assertTrue(subscription.id() > 0, "ID should be present");
        assertEquals("test-subscription", subscription.code());
        assertEquals(subscription.id(), dto.id());
        assertEquals(2, subscription.eventTypes().size());
        assertEquals(subscription.eventTypes().get(0).eventType(), "test-event-type-1");
        assertEquals(subscription.eventTypes().get(1).eventType(), "test-event-type-2");
    }
}
