package com.soignemoi.soignemoiapi.data.dto.note;

import lombok.Data;

import java.util.Date;

@Data
public class ResultNoteDto {

    private int id;
    private int appointmentId;
    private int doctorId;
    private int userId;
    private String title;
    private String content;
    private Date date;

    public ResultNoteDto() {}

    public ResultNoteDto(
            int id,
            int appointmentId,
            int doctorId,
            int userId,
            String title,
            String content,
            Date date
    ) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.date = date;
    }

}
