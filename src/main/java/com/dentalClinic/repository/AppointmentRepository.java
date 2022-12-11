package com.dentalClinic.repository;

import com.dentalClinic.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a WHERE a.patient.name = ?1")
    List<Appointment> getAppointmentsByPatientName(String patientName);
}
