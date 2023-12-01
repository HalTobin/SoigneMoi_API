package com.soignemoi.soignemoiapi.data.dto.appointment;

import com.soignemoi.soignemoiapi.data.dto.VisitorDto;
import com.soignemoi.soignemoiapi.data.dto.doctor.DoctorDto;
import com.soignemoi.soignemoiapi.data.models.Specialty;
import lombok.Data;

@Data
public class AppointmentForVisitDto {

    AppointmentDto appointment;
    int lastVisit;
    DoctorDto doctor;
    VisitorDto visitor;
    boolean visitForToday;

    public AppointmentForVisitDto(
            AppointmentDto appointment,
            int lastVisit,
            DoctorDto doctor,
            VisitorDto visitor,
            boolean visitForToday
    ) {
        this.appointment = appointment;
        this.lastVisit = lastVisit;
        this.doctor = doctor;
        this.visitor = visitor;
        this.visitForToday = visitForToday;
    }

}
