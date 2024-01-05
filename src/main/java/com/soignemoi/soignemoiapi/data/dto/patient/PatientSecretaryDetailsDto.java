package com.soignemoi.soignemoiapi.data.dto.patient;

import com.soignemoi.soignemoiapi.data.dto.appointment.AppointmentDto;
import com.soignemoi.soignemoiapi.data.dto.doctor.DoctorDto;
import com.soignemoi.soignemoiapi.data.dto.prescription.PrescriptionDto;
import com.soignemoi.soignemoiapi.data.models.Note;
import com.soignemoi.soignemoiapi.data.models.Specialty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PatientSecretaryDetailsDto {

    private int id;
    private String name;
    private String surname;
    private String mail;
    private AppointmentDto appointment;
    private DoctorDto doctor;
    private Specialty specialty;
    private Date startDate;
    private Date endDate;
    private List<Note> notes;
    private List<PrescriptionDto> prescriptions;

    public PatientSecretaryDetailsDto(
            int id,
            String name,
            String surname,
            String mail,
            AppointmentDto appointment,
            DoctorDto doctor,
            Specialty specialty,
            Date startDate,
            Date endDate,
            List<Note> notes,
            List<PrescriptionDto> prescriptions
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.appointment = appointment;
        this.doctor = doctor;
        this.specialty = specialty;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
        this.prescriptions = prescriptions;
    }

}
