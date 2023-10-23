package com.kramti.repository;

import com.kramti.entity.Shelter;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShelterRepository implements PanacheRepository<Shelter> {
}
