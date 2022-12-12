package com.dentalClinic.controller;

import com.dentalClinic.dto.DentistDTO;
import com.dentalClinic.entity.Dentist;
import com.dentalClinic.exceptions.NotFoundException;
import com.dentalClinic.service.impl.DentistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DentistController {

    private final DentistServiceImpl dentistService;

    @Autowired
    public DentistController(DentistServiceImpl dentistService) {
        this.dentistService = dentistService;
    }

    @PostMapping("/dentist/new")
    public ResponseEntity<Dentist> dentistRecord(@RequestBody Dentist dentist) {
        return ResponseEntity.ok(dentistService.dentistRecord(dentist));
    }

    @PutMapping("/dentist/update")
    public ResponseEntity<Dentist> updateDentist(@RequestBody Dentist dentist) throws NotFoundException {
        ResponseEntity<Dentist> response = null;

        if (dentist.getId() != null && dentistService.findDentistById(dentist.getId()) != null) {
            response = ResponseEntity.ok(dentistService.updateDentist(dentist));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/dentist/{id}")
    public DentistDTO findDentistById(@PathVariable Long id) throws NotFoundException {
        return dentistService.findDentistById(id);
    }

    @GetMapping("/dentists")
    public ResponseEntity<List<Dentist>> searchAllDentists() {
        return ResponseEntity.ok(dentistService.searchAllDentists());
    }

    @DeleteMapping("/dentist/{id}")
    public ResponseEntity<String> removeDentist(@PathVariable Long id) throws NotFoundException {
        ResponseEntity<String> response = null;

        dentistService.removeDentist(id);
        response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");

        return response;
    }

    @GetMapping("/dentist/{name}")
    public ResponseEntity<Dentist> searchByName(@PathVariable String name) {
        Dentist dentist = dentistService.searchByName(name);
        return ResponseEntity.ok(dentist);
    }
}
