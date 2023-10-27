package com.soignemoi.soignemoiapi.repository;

import com.soignemoi.soignemoiapi.data.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
    Visitor findById(int id);
    Visitor findByMail(String mail);

    List<Visitor> findAll();
}

