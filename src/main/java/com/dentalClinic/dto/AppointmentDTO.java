package com.dentalClinic.dto;

import com.dentalClinic.entity.Dentist;
import com.dentalClinic.entity.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDTO {

    private Long id;
    private Dentist dentist;
    private Patient patient;
    private Date date;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Long id, Dentist dentist, Patient patient, Date date) {
        this.id = id;
        this.dentist = dentist;
        this.patient = patient;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
                "id=" + id +
                ", dentist=" + dentist +
                ", patient=" + patient +
                ", date=" + date +
                '}';
    }
}
