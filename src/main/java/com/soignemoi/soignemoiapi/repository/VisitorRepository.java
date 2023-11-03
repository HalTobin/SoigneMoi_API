package com.soignemoi.soignemoiapi.repository;

import com.soignemoi.soignemoiapi.data.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VisitorRepository extends JpaRepository<Visitor, Integer> {

    Visitor save(Visitor visitor);
    Visitor findById(int id);
    Optional<Visitor> findByMail(String mail);
    List<Visitor> findAll();
    boolean existsByMail(String mail);
}

