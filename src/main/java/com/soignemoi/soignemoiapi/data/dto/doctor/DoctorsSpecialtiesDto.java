package com.soignemoi.soignemoiapi.data.dto.doctor;

import com.soignemoi.soignemoiapi.data.models.Specialty;
import lombok.Data;

import java.util.List;

@Data
public class DoctorsSpecialtiesDto {

    private List<DoctorDto> doctors;
    private List<Specialty> specialties;

    public DoctorsSpecialtiesDto(List<DoctorDto> doctors, List<Specialty> specialties) {
        this.doctors = doctors;
        this.specialties = specialties;
    }
}
