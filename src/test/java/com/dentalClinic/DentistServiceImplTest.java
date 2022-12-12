package com.dentalClinic;

import com.dentalClinic.dto.DentistDTO;
import com.dentalClinic.entity.Dentist;
import com.dentalClinic.exceptions.NotFoundException;
import com.dentalClinic.repository.DentistRepository;
import com.dentalClinic.service.impl.DentistServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Set;

/*After each Test Database context is reset*/
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class DentistServiceImplTest {

    private final DentistServiceImpl dentistService;
    private final DentistRepository dentistRepository;

    @Autowired
    public DentistServiceImplTest(DentistServiceImpl dentistService, DentistRepository dentistRepository) {
        this.dentistService = dentistService;
        this.dentistRepository = dentistRepository;
    }

    @BeforeEach
    public void createDentists(){
        Dentist firstDentist = new Dentist("Lorena", "Perez", "LP123");
        dentistRepository.save(firstDentist);

        Dentist secondDentist = new Dentist("Marco", "Martinez", "MM123");
        dentistRepository.save(secondDentist);

        Dentist thirdDentist = new Dentist("Carol", "Pazote", "CP123");
        dentistRepository.save(thirdDentist);

        Dentist fourthDentist = new Dentist("Pablo", "Lucca", "PL123");
        dentistRepository.save(fourthDentist);
    }

    @Test
    public void dentistToDTOTest(){

        Dentist firstDentist = new Dentist("Carlos", "Lopez", "CL123");

        ObjectMapper mapper = new ObjectMapper();

        DentistDTO dentistDTO = mapper.convertValue(firstDentist, DentistDTO.class);
        Assertions.assertNotNull(dentistDTO);
    }

    @Test
    public void findDentistByIdTest() throws NotFoundException {
        Assertions.assertNotNull(dentistService.findDentistById(1L));
    }
}
