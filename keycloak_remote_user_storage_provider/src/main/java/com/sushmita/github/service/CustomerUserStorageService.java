package com.sushmita.github.service;

import com.sushmita.github.model.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
public interface CustomerUserStorageService {

    @GET
    @Path("/get/{username}")
    UserDTO getUserDetails(@PathParam("username") String username);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/authenticate/{username}")
    UserDTO verifyUserPassword(@PathParam("username") String username, String password);
}
