package com.kleberaluizio.coffee;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class MyResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPlain(){
        return "Got it";
    }
}
