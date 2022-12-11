package com.dentalClinic.service.impl;

import com.dentalClinic.entity.Patient;
import com.dentalClinic.exceptions.NotFoundException;
import com.dentalClinic.repository.PatientRepository;
import com.dentalClinic.service.PatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger LOGGER = Logger.getLogger(DentistServiceImpl.class);

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient patientRecord(Patient patient) {
        LOGGER.info("Registration method of a patient");
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient findPatientById(Long id) throws NotFoundException {
        if (patientRepository.findById(id).isEmpty()) {
            throw new NotFoundException("There is not patient with id: " + id);
        }
        return patientRepository.findById(id).get();
    }

    public List<Patient> searchAllPatients(){
        return patientRepository.findAll();
    }

    public void removePatient(Long id) throws NotFoundException {
        if (Objects.nonNull(findPatientById(id))) {
            patientRepository.deleteById(id);
        }
    }

    public Patient searchByDni(String dni){
        return patientRepository.searchByDni(dni).get();
    }
}
