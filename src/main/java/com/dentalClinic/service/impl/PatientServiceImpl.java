package com.dentalClinic.service.impl;

import com.dentalClinic.dto.PatientDTO;
import com.dentalClinic.entity.Patient;
import com.dentalClinic.exceptions.NotFoundException;
import com.dentalClinic.repository.PatientRepository;
import com.dentalClinic.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger LOGGER = Logger.getLogger(DentistServiceImpl.class);

    private final PatientRepository patientRepository;
    private final ObjectMapper mapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ObjectMapper mapper) {
        this.patientRepository = patientRepository;
        this.mapper = mapper;
    }


    public Patient patientRecord(Patient patient) {
        LOGGER.info("Registration method of a patient");
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Patient patient){
        return patientRepository.save(patient);
    }

    public PatientDTO findPatientById(Long id) throws NotFoundException {

        Optional<Patient> patient = patientRepository.findById(id);
        PatientDTO patientDTO;

        if (patient.isEmpty()) {
            LOGGER.error("ERROR! Patient with Id " + id + " was not found");
            throw new NotFoundException("Patient with Id " + id + " was not found");
        } else {
            patientDTO = mapper.convertValue(patient, PatientDTO.class);
        }
        return patientDTO;
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
