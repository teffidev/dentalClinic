package com.dentalClinic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dentists")
public class Dentist {

    @Id
    @SequenceGenerator(name = "dentist_sequence", sequenceName = "dentist_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dentist_sequence")
    private Long id;
    private String name;
    private String lastname;
    @Column(name = "num_license")
    private String license;


    @OneToMany(mappedBy = "dentist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Appointment> appointments;


    public Dentist(){
    }

    public Dentist(String name, String lastname, String license) {
        this.name = name;
        this.lastname = lastname;
        this.license = license;
    }

    public Dentist(Long id, String name, String lastname, String license) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.license = license;
    }

    public Dentist(Long id, String name, String lastname, String license, List<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.license = license;
        this.appointments = appointments;
    }


    public Long getId() {
        return id;
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

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "Dentist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", license='" + license + '\'' +
                '}';
    }
}
