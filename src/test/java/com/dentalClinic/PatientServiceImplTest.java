package com.dentalClinic;

import com.dentalClinic.dto.PatientDTO;
import com.dentalClinic.entity.Patient;
import com.dentalClinic.exceptions.NotFoundException;
import com.dentalClinic.repository.PatientRepository;
import com.dentalClinic.service.impl.PatientServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class PatientServiceImplTest {

    private final PatientServiceImpl patientService;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImplTest(PatientServiceImpl patientService, PatientRepository patientRepository) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }

    @BeforeEach
    public void createPatients() {
        Patient firstPatient = new Patient("Olga", "Ortiz", "Calle 10 Avenida", "DNI897456", new Date());
        patientRepository.save(firstPatient);


        Patient secondPatient = new Patient("Patricia", "Parra", "Avenida Fenix", "DNI365412", new Date());
        patientRepository.save(secondPatient);


        Patient thirdPatient = new Patient("Dan", "Linares", "12 street Avenida", "DNI8700", new Date());
        patientRepository.save(thirdPatient);


        Patient fourthPatient = new Patient("Rafael", "Hoyos", "Street Olaya", "DNI00056", new Date());
        patientRepository.save(fourthPatient);
    }

    @Test
    public void patientToDTOTest() {
        Patient patient = new Patient("Otoniel", "Botero", "Carrera viva envigado", "DNI1010", new Date());

        ObjectMapper mapper = new ObjectMapper();

        PatientDTO patientDTO = mapper.convertValue(patient, PatientDTO.class);

        Assertions.assertNotNull(patientDTO);
    }


}
