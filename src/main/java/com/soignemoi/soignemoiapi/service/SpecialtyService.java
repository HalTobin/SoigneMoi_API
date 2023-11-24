package com.soignemoi.soignemoiapi.service;

import com.soignemoi.soignemoiapi.data.models.Specialty;
import com.soignemoi.soignemoiapi.error.ValueNotFoundException;
import com.soignemoi.soignemoiapi.repository.SpecialtyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyService {

    private static final Logger logger = LoggerFactory.getLogger(SpecialtyService.class);

    @Autowired
    private SpecialtyRepository specialtyRepository;

    public void create(Specialty newSpecialty) {
        specialtyRepository.save(newSpecialty);
    }

    public Specialty loadById(int id) throws ValueNotFoundException {
        return specialtyRepository
                .findById(id)
                .orElseThrow(() -> new ValueNotFoundException(String.format("No specialty found for this id:%13d ", id)));
    }

    public List<Specialty> loadSpecialties() {
        return specialtyRepository.findAll();
    }

}
