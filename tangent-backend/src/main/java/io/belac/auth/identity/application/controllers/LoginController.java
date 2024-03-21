package io.belac.auth.identity.application.controllers;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class LoginController {

    @POST
    @Transactional
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {

        return Response.ok().build();
    }





}
