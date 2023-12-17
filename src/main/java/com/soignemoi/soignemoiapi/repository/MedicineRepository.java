package com.soignemoi.soignemoiapi.repository;

import com.soignemoi.soignemoiapi.data.models.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    Medicine save(Medicine medicine);
    List<Medicine> findAll();
    Medicine findById(int id);
}
