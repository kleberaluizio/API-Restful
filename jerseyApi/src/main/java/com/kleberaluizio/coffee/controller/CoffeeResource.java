package com.kleberaluizio.coffee.controller;

import com.kleberaluizio.coffee.model.Coffee;
import com.kleberaluizio.coffee.model.repository.CoffeeRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/coffee")
public class CoffeeResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        List<Coffee> cafes = CoffeeRepository.findAll();
        return Response.status(Response.Status.OK).entity(cafes).build();
    }
}
