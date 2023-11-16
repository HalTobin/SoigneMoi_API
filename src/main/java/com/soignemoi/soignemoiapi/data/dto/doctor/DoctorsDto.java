package com.soignemoi.soignemoiapi.data.dto.doctor;

import com.soignemoi.soignemoiapi.data.models.Doctor;
import lombok.Data;

import java.util.List;

@Data
public class DoctorsDto {

    private List<DoctorDto> doctors;

    public DoctorsDto(List<DoctorDto> doctors) { this.doctors = doctors; }
}
