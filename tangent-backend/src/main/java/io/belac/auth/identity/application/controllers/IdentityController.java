package io.belac.auth.identity.application.controllers;

import com.fasterxml.uuid.Generators;
import com.github.javafaker.Faker;
import io.belac.auth.identity.domain.commands.CreateUserIdentity;
import io.belac.auth.identity.domain.events.UserIdentityCreated;
import io.belac.auth.identity.infrastructure.entities.UserIdentityEntity;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/auth/identity")
public class IdentityController {

    Logger logger = LoggerFactory.getLogger(IdentityController.class);

    @POST
    @Path("/user")
    @Transactional
    public Response creatUser(CreateUserIdentity createUser) {
        var faker = new Faker();
        var pass = faker.book().title();
        logger.info(pass);
        var user = new UserIdentityEntity();
        user.id = Generators.timeBasedEpochGenerator().generate().toString();
        user.email = createUser.email();
        user.password = faker.book().title();
        user.rememberMe = "";
        user.username = StringUtils.substringBefore(user.email, "@");
        user.name = faker.name().fullName();
        user.persist();
        return Response.ok(new UserIdentityCreated(user.id)).build();
    }
}


