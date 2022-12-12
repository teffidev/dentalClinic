package com.dentalClinic.controller;

import com.dentalClinic.dto.AppointmentDTO;
import com.dentalClinic.entity.Appointment;
import com.dentalClinic.exceptions.ErrorRequestException;
import com.dentalClinic.exceptions.NotFoundException;
import com.dentalClinic.service.impl.AppointmentServiceImpl;
import com.dentalClinic.service.impl.DentistServiceImpl;
import com.dentalClinic.service.impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.SecondaryTable;
import java.util.List;
import java.util.Set;

@RestController
public class AppointmentController {

    private final AppointmentServiceImpl appointmentService;
    private final DentistServiceImpl dentistService;
    private final PatientServiceImpl patientService;

    @Autowired
    public AppointmentController(AppointmentServiceImpl appointmentService, DentistServiceImpl dentistService, PatientServiceImpl patientService) {
        this.appointmentService = appointmentService;
        this.dentistService = dentistService;
        this.patientService = patientService;
    }

    @PostMapping("/appointment/new")
    public ResponseEntity<Appointment> appointmentRecord(@RequestBody Appointment appointment) throws ErrorRequestException, NotFoundException {
        ResponseEntity<Appointment> response;
        if (patientService.findPatientById(appointment.getPatient().getId()) != null && dentistService.findDentistById(appointment.getDentist().getId()) != null) {
            response = ResponseEntity.ok(appointmentService.appointmentRecord(appointment));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @PutMapping("/appointment/update")
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.updateAppointment(appointment));
    }

    @GetMapping("/appointment/{id}")
    public ResponseEntity<Appointment> findAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.findAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/appointments")
    public Set<AppointmentDTO> searchAllAppointments() {
        return appointmentService.searchAllAppointments();
    }

    @DeleteMapping("/appointment/{id}")
    public ResponseEntity<String> removeAppointment(@PathVariable Long id) throws NotFoundException {
        ResponseEntity<String> response;

        if (appointmentService.findAppointmentById(id) != null) {
            appointmentService.removeAppointment(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted appointment");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/appointment/{name}")
    public ResponseEntity<String> getAppointmentsByPatientName(@PathVariable String patientName) {
        List<Appointment> appointment = appointmentService.getAppointmentsByPatientName(patientName);
        return ResponseEntity.ok(patientName);
    }

}