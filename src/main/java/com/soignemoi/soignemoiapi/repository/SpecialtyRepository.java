package com.soignemoi.soignemoiapi.repository;

import com.soignemoi.soignemoiapi.data.models.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {
    List<Specialty> findAll();
    Optional<Specialty> findByTitle(String title);
}
