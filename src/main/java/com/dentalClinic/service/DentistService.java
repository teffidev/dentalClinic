package com.dentalClinic.service;

import com.dentalClinic.dto.DentistDTO;
import com.dentalClinic.entity.Dentist;
import com.dentalClinic.exceptions.NotFoundException;

import java.util.List;

public interface DentistService {
    Dentist dentistRecord(Dentist dentist);
    Dentist updateDentist(Dentist dentist);
    DentistDTO findDentistById(Long id) throws NotFoundException;
    List<Dentist> searchAllDentists();
    void removeDentist(Long id) throws NotFoundException;
    Dentist searchByName(String name);
}
