package com.kramti.service;

import com.kramti.dto.ErrorDto;
import com.kramti.entity.Shelter;
import com.kramti.repository.ShelterRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;

@ApplicationScoped
public class ShelterService {
    @Inject
    ShelterRepository shelterRepository;
    public Response listAll() {
        return Response
                .ok(shelterRepository.listAll())
                .build();
    }
    @Transactional
    public Response add(Shelter shelter) {
        if (shelter.getId() != null){
            Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorDto("Id is auto generated!"))
                    .build();
        }
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
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorDto("Shelter not found"))
                    .build();
        }
        dbShelter.setApproved(shelter.isApproved());
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
}
