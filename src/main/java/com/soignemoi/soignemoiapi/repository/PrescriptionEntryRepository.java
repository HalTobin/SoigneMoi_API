package com.soignemoi.soignemoiapi.repository;

import com.soignemoi.soignemoiapi.data.models.PrescriptionEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionEntryRepository extends JpaRepository<PrescriptionEntry, Integer> {

    PrescriptionEntry save(PrescriptionEntry entry);

}
