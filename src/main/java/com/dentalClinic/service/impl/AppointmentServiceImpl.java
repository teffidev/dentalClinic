package com.dentalClinic.service.impl;

import com.dentalClinic.entity.Appointment;
import com.dentalClinic.entity.Dentist;
import com.dentalClinic.entity.Patient;
import com.dentalClinic.exceptions.ErrorRequestException;
import com.dentalClinic.exceptions.NotFoundException;
import com.dentalClinic.repository.AppointmentRepository;
import com.dentalClinic.repository.DentistRepository;
import com.dentalClinic.repository.PatientRepository;
import com.dentalClinic.service.AppointmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger LOGGER = Logger.getLogger(AppointmentServiceImpl.class);

    private final AppointmentRepository appointmentRepository;
    private final DentistRepository dentistRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DentistRepository dentistRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.dentistRepository = dentistRepository;
        this.patientRepository = patientRepository;
    }


    public Appointment appointmentRecord(Appointment appointment) throws ErrorRequestException {
        Optional<Dentist> dentist = dentistRepository.findById(appointment.getDentist().getId());

        Optional<Patient> patient = patientRepository.findById(appointment.getPatient().getId());

        if (patient.isEmpty() || dentist.isEmpty()) {
            throw new ErrorRequestException("Neither the patient nor the dentist exist!");
        }

        appointment.setDentist(dentist.get());
        appointment.setPatient(patient.get());

        appointment.setDate(new Date());
        LOGGER.info("Appointment saved successfully!");
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public Appointment findAppointmentById(Long id) {
        return appointmentRepository.findById(id).get();
    }

    public List<Appointment> searchAllAppointments(){
        return appointmentRepository.findAll();
    }

    public void removeAppointment(Long id) throws NotFoundException {
        if (Objects.isNull(findAppointmentById(id))) {
            throw new NotFoundException("Does not exist an Appointment with Id: " + id);
        }
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> getAppointmentsByPatientName(String patientName) {
        return appointmentRepository.getAppointmentsByPatientName(patientName);
    }
}
