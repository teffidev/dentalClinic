package com.dentalClinic.service;

import com.dentalClinic.entity.Patient;
import com.dentalClinic.exceptions.NotFoundException;

import java.util.List;

public interface PatientService {
    Patient patientRecord(Patient patient);
    Patient updatePatient(Patient patient);
    Patient findPatientById(Long id) throws NotFoundException;
    List<Patient> searchAllPatients();
    void removePatient(Long id) throws NotFoundException;
    Patient searchByDni(String dni);
}
