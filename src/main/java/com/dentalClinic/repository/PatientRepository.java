package com.dentalClinic.repository;

import com.dentalClinic.entity.Dentist;
import com.dentalClinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT p.dni FROM Patient p WHERE p.dni = ?1") /*HQL-Spring Boot*/
    Optional<Patient> searchByDni(String dni);

}
