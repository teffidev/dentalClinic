package com.dentalClinic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @SequenceGenerator(name = "patient_sequence", sequenceName = "patient_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_sequence")
    private Long id;
    private String name;
    private String lastname;
    private String address;
    @NotBlank
    @Size(min = 0, max = 20)
    private String dni;
    private Date admissionDate;


    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Appointment> appointments;


    public Patient() {
    }

    public Patient(String name, String lastname, String address, String dni, Date admissionDate) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.dni = dni;
        this.admissionDate = admissionDate;
    }

    public Patient(Long id, String name, String lastname, String address, String dni, Date admissionDate) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.dni = dni;
        this.admissionDate = admissionDate;
    }

    public Patient(Long id, String name, String lastname, String address, String dni, Date admissionDate, List<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.dni = dni;
        this.admissionDate = admissionDate;
        this.appointments = appointments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", dni='" + dni + '\'' +
                ", admissionDate=" + admissionDate +
                ", appointments=" + appointments +
                '}';
    }
}
