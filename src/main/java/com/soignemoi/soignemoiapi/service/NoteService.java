package com.soignemoi.soignemoiapi.service;

import com.soignemoi.soignemoiapi.data.models.Note;
import com.soignemoi.soignemoiapi.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    public List<Note> getNotesByAppointmentId(int appointmentId) {
        return noteRepository.findByAppointmentId(appointmentId);
    }

}
