package com.kramti.controller;

import com.kramti.entity.Shelter;
import com.kramti.service.ShelterService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


@Path("/api/shelters")
public class ShelterController {
    @Inject
    ShelterService shelterService;
    @GET
    public Response get(){
        return this.shelterService.listAll();
    }

    @POST
    public Response post(Shelter shelter) {
        return this.shelterService.add(shelter);
    }
    @PUT
    public Response put(Shelter shelter) {
        return this.shelterService.update(shelter);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        return this.shelterService.delete(id);
    }

}
