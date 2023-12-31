package com.kramti.service;

import com.kramti.config.AppConfig;
import com.kramti.dto.ErrorDto;
import com.kramti.entity.Shelter;
import com.kramti.repository.ShelterRepository;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

@ApplicationScoped
public class ShelterService {
    @Inject
    ShelterRepository shelterRepository;
    @Inject
    SecurityIdentity securityIdentity;
    public Response listAll() {
        return Response
                .ok(shelterRepository.listAll())
                .build();
    }
    @Transactional
    public Response add(Shelter shelter) {
        String loggedInUsername = securityIdentity.getPrincipal().getName();
        if (shelter.getId() != null){
            Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorDto("Id is auto generated!"))
                    .build();
        }
        shelter.setAddedBy(loggedInUsername);
        shelter.setAddedDate(LocalDate.now());
        shelter.setApproved(null);
        this.shelterRepository.persist(shelter);
        return this.generateSaveResponse(shelter);
    }
    @Transactional
    public Response update(Shelter shelter) {
        if (shelter.getId() == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorDto("Please, provide the shelter id!"))
                    .build();
        }
        Shelter dbShelter = this.shelterRepository.findById(shelter.getId());
        if (dbShelter == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorDto(AppConfig.SHELTER_NOT_FOUND_MESSAGE))
                    .build();
        }
        dbShelter.setApproved(shelter.getApproved() == true);
        dbShelter.setName(shelter.getName());
        dbShelter.setCapacity(shelter.getCapacity());
        dbShelter.setDescription(shelter.getDescription());
        dbShelter.setLocation(shelter.getLocation());

        this.shelterRepository.persist(dbShelter);
        return this.generateSaveResponse(dbShelter);
    }

    public Response generateSaveResponse(Shelter shelter){
        try{
            return Response
                    .created(new URI("http://localhost:8080/api/shelters/" + shelter.getId()))
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
    @Transactional
    public Response delete(Long id) {
        this.shelterRepository.deleteById(id);
        return Response
                .noContent()
                .build();
    }

    public Response listApproved() {
        return Response
                .ok(this.shelterRepository.list("approved", true))
                .build();
    }

    public Response findById(long id) {
        Shelter shelter = this.shelterRepository.findById(id);
        if (shelter == null){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorDto(AppConfig.SHELTER_NOT_FOUND_MESSAGE))
                    .build();
        }
        return Response
                .ok(shelter)
                .build();
    }

    public Response listPendingApproval() {
        return Response
                .ok(this.shelterRepository.list("approved = null"))
                .build();
    }
    @Transactional
    public Response approve(Shelter shelter) {
        Shelter shelter1 = this.shelterRepository.findById(shelter.getId());
        shelter1.setApproved(true);
        this.shelterRepository.persist(shelter1);
        return this.generateSaveResponse(shelter1);
    }
}
