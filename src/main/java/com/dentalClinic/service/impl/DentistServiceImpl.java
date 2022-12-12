package com.dentalClinic.service.impl;

import com.dentalClinic.dto.DentistDTO;
import com.dentalClinic.entity.Dentist;
import com.dentalClinic.exceptions.NotFoundException;
import com.dentalClinic.repository.DentistRepository;
import com.dentalClinic.service.DentistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DentistServiceImpl implements DentistService {

    private static final Logger LOGGER = Logger.getLogger(DentistServiceImpl.class);

    private final DentistRepository dentistRepository;
    private final ObjectMapper mapper;

    @Autowired
    public DentistServiceImpl(DentistRepository dentistRepository, ObjectMapper mapper) {
        this.dentistRepository = dentistRepository;
        this.mapper = mapper;
    }


    public Dentist dentistRecord(Dentist dentist) {
        LOGGER.info("Registration method of a dentist");
        return dentistRepository.save(dentist);
    }

    public Dentist updateDentist(Dentist dentist){
        return dentistRepository.save(dentist);
    }

    public DentistDTO findDentistById(Long id) throws NotFoundException {

        Optional<Dentist> dentist = dentistRepository.findById(id);
        DentistDTO dentistDTO;

        if (dentist.isEmpty()) {
            LOGGER.error("ERROR! Dentist with Id " + id + " was not found");
            throw new NotFoundException("Dentist with Id " + id + " was not found");
        } else {
            dentistDTO = mapper.convertValue(dentist, DentistDTO.class);
        }
        return dentistDTO;
    }

    public List<Dentist> searchAllDentists(){

        return dentistRepository.findAll();
    }

    public void removeDentist(Long id) throws NotFoundException {
        if (Objects.nonNull(findDentistById(id))) {
            dentistRepository.deleteById(id);
        }
    }

    public Dentist searchByName(String name){
        return dentistRepository.searchName(name).get();
    }
}
