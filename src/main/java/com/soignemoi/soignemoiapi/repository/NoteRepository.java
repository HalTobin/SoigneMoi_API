package com.soignemoi.soignemoiapi.repository;

import com.soignemoi.soignemoiapi.data.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    Note save(Note note);
    List<Note> findByAppointmentId(int appointmentId);

}
