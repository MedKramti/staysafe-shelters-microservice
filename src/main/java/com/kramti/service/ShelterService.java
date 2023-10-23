package com.kramti.service;

import com.kramti.repository.ShelterRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ShelterService {
    @Inject
    ShelterRepository shelterRepository;
    public Response listAll() {
        return Response
                .ok(shelterRepository.listAll())
                .build();
    }
}
