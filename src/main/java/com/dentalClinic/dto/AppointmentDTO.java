package com.dentalClinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDTO {

    private Date date;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "date=" + date +
                '}';
    }
}
