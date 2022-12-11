package com.dentalClinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDTO {

    private String name;
    private String lastname;
    private Date admissionDate;

    public PatientDTO() {
    }

    public PatientDTO(String name, String lastname, Date admissionDate) {
        this.name = name;
        this.lastname = lastname;
        this.admissionDate = admissionDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admisionDate) {
        this.admissionDate = admisionDate;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", admissionDate=" + admissionDate +
                '}';
    }
}
