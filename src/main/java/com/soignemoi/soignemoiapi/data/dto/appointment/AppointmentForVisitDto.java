package com.soignemoi.soignemoiapi.data.dto.appointment;

import com.soignemoi.soignemoiapi.data.dto.VisitorDto;
import com.soignemoi.soignemoiapi.data.dto.doctor.DoctorForVisitDto;
import lombok.Data;

@Data
public class AppointmentForVisitDto {

    AppointmentDto appointment;
    int lastVisit;
    DoctorForVisitDto doctor;
    VisitorDto visitor;
    boolean visitForToday;

    public AppointmentForVisitDto(
            AppointmentDto appointment,
            int lastVisit,
            DoctorForVisitDto doctor,
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
