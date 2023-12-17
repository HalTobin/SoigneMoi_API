package com.soignemoi.soignemoiapi.service;

import com.soignemoi.soignemoiapi.data.models.Medicine;
import com.soignemoi.soignemoiapi.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    @Autowired
    MedicineRepository medicineRepository;

    public Medicine createMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public Medicine getById(int id) {
        return medicineRepository.findById(id);
    }

}
