package com.dentalClinic.repository;

import com.dentalClinic.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Long> {

    @Query("SELECT d.name FROM Dentist d WHERE d.name = ?1") /*HQL-Spring Boot*/
    Optional<Dentist> searchName(String name);

}
