package com.kramti.controller;

import com.kramti.entity.Shelter;
import com.kramti.service.ShelterService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


@Path("/api/shelters")
public class ShelterController {
    @Inject
    ShelterService shelterService;
    @GET
    @Path("/all")
    @RolesAllowed("admin")
    public Response get(){
        return this.shelterService.listAll();
    }

    @GET
    @Path("/pending-approval")
    @RolesAllowed("admin")
    @PermitAll
    public Response getPending(){
        return this.shelterService.listPendingApproval();
    }

    @GET
    @PermitAll
    public Response getApprovedShelter(){
        return this.shelterService.listApproved();
    }
    @GET
    @Path("/{id}")
    @PermitAll
    public Response getById(@PathParam("id") long id){
        return this.shelterService.findById(id);
    }

    @POST
    @RolesAllowed({"user", "admin"})
    public Response post(@Valid Shelter shelter) {
        return this.shelterService.add(shelter);
    }
    @PUT
    @RolesAllowed("admin")
    public Response put(Shelter shelter) {
        return this.shelterService.update(shelter);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response delete(@PathParam("id") Long id){
        return this.shelterService.delete(id);
    }

    @PUT
    @Path("/approve")
    @RolesAllowed("admin")
    public Response approve(Shelter shelter) {
        return this.shelterService.approve(shelter);
    }

}
