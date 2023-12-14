package com.soignemoi.soignemoiapi.data.dto.patient;

import com.soignemoi.soignemoiapi.data.models.Note;
import com.soignemoi.soignemoiapi.data.models.Prescription;
import com.soignemoi.soignemoiapi.data.models.Specialty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PatientDetailsDto {

    private int id;
    private String name;
    private String surname;
    private String reason;
    private Specialty specialty;
    private Date startDate;
    private Date endDate;
    private List<Note> notes;
    private List<Prescription> prescriptions;

    public PatientDetailsDto(
            int id,
            String name,
            String surname,
            String reason,
            Specialty specialty,
            Date startDate,
            Date endDate,
            List<Note> notes,
            List<Prescription> prescriptions
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.reason = reason;
        this.specialty = specialty;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
        this.prescriptions = prescriptions;
    }

}
