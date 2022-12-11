package com.dentalClinic.controller;

import com.dentalClinic.entity.Patient;
import com.dentalClinic.exceptions.NotFoundException;
import com.dentalClinic.service.impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    private final PatientServiceImpl patientService;

    @Autowired
    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/patient/new")
    public ResponseEntity<Patient> patientRecord(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.patientRecord(patient));
    }

    @PutMapping("/patient/update")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) throws NotFoundException {
        ResponseEntity<Patient> response = null;

        if (patient.getId() != null && patientService.findPatientById(patient.getId()) != null) {
            response = ResponseEntity.ok(patientService.updatePatient(patient));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<Patient> findPatientById(@PathVariable Long id) throws NotFoundException {
        Patient patient = patientService.findPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> searchAllPatients() {
        return ResponseEntity.ok(patientService.searchAllPatients());
    }

    @DeleteMapping("/patient/{id}")
    public ResponseEntity<String> removePatient(@PathVariable Long id) throws NotFoundException {
        ResponseEntity<String> response = null;

        patientService.removePatient(id);
        response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");

        return response;
    }

    @GetMapping("/patient/{dni}")
    public ResponseEntity<Patient> searchByDni(@PathVariable String dni) {
        Patient patient = patientService.searchByDni(dni);
        return ResponseEntity.ok(patient);
    }
}
