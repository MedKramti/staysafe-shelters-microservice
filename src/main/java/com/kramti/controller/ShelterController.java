package com.kramti.controller;

import com.kramti.service.ShelterService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;


@Path("/api/shelters")
public class ShelterController {
    @Inject
    ShelterService shelterService;
    @GET
    public Response get(){
        return this.shelterService.listAll();
    }


}
