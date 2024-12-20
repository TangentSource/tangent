package io.belac.tangent.test.controllers.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.belac.tangent.core.event.domain.commands.CreateEventCommand;
import io.belac.tangent.core.event.domain.dtos.EventContextDto;
import io.belac.tangent.core.event.domain.dtos.EventDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class EventCreateTest {

    @Inject
    ObjectMapper objectMapper;

    @Test
    public void testCreate() throws JsonProcessingException {
        var context1 = new EventContextDto("key1", "val1");
        var context2 = new EventContextDto("key2", "val2");
        var contexts = new ArrayList<EventContextDto>();
        contexts.add(context1);
        contexts.add(context2);

        var command = new CreateEventCommand(
                "test-event",
                "1.0",
                "test-source",
                "test-subject",
                OffsetDateTime.now(),
                "application/json",
                "{\"test\":\"data\"}",
                "test-message-group",
                "test-deduplication-id",
                "test-module",
                contexts
        );
        var commandJson = objectMapper.writeValueAsString(command);


        Response response = given()
                .when()
                .header("Content-Type", "application/json")
                .body(commandJson)
                .post("/event/create")
                .then()
                .statusCode(200)
                .extract()
                .response();
        var dto = response.as(EventDto.class);
        assertTrue(dto.id() > 0, "ID should be present");

        //lets get it back
        response = given()
                .when()
                .get("/event/" + dto.id())
                .then()
                .statusCode(200)
                .extract()
                .response();
        var event = response.as(EventDto.class);
        assertTrue(event.id() > 0, "ID should be present");
        assertEquals("test-event", event.type());
        assertEquals(event.id(), dto.id());
    }
}
