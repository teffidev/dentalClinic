package com.dentalClinic.service.impl;

import com.dentalClinic.entity.Dentist;
import com.dentalClinic.exceptions.NotFoundException;
import com.dentalClinic.repository.DentistRepository;
import com.dentalClinic.service.DentistService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DentistServiceImpl implements DentistService {

    private static final Logger LOGGER = Logger.getLogger(DentistServiceImpl.class);

    private final DentistRepository dentistRepository;

    @Autowired
    public DentistServiceImpl(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    public Dentist dentistRecord(Dentist dentist) {
        LOGGER.info("Registration method of a dentist");
        return dentistRepository.save(dentist);
    }

    public Dentist updateDentist(Dentist dentist){
        return dentistRepository.save(dentist);
    }

    public Dentist findDentistById(Long id) throws NotFoundException {
        if (dentistRepository.findById(id).isEmpty()) {
            throw new NotFoundException("There is not dentist with id: " + id);
        }
        return dentistRepository.findById(id).get();
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
