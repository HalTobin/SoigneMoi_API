package com.soignemoi.soignemoiapi.data.dto.prescription;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class PrescriptionDto {

    private Integer id;
    private int appointmentId;
    private Date start;
    private Date end;
    private List<EntryDto> entries;

    @Override
    public String toString() {
        return "{ id:" + id.toString() +
                ", appointmentId: " + appointmentId +
                ", start: " + start +
                ", end:" + end +
                ", entries: " + entries.size() +
                " }";
    }

}
