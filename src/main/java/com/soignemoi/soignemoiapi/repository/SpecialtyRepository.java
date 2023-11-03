package com.soignemoi.soignemoiapi.repository;

import com.soignemoi.soignemoiapi.data.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {
    List<Specialty> findAll();
}
