package com.soignemoi.soignemoiapi.data.dto.doctor;

import com.soignemoi.soignemoiapi.data.models.Doctor;

import java.util.List;

public class DoctorsDto {

    private List<Doctor> doctors;

    public DoctorsDto(List<Doctor> doctors) { this.doctors = doctors; }
}
