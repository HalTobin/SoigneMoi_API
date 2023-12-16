package com.soignemoi.soignemoiapi.data.dto.note;

import lombok.Data;

@Data
public class AddNoteDto {

    private int appointmentId;
    private int doctorId;
    private int userId;
    private String title;
    private String content;

    public AddNoteDto() {}

    public AddNoteDto(
            int appointmentId,
            int doctorId,
            int userId,
            String title,
            String content
    ) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

}
