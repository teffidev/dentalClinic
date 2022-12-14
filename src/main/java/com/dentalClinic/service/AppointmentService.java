package com.dentalClinic.service;

import com.dentalClinic.dto.AppointmentDTO;
import com.dentalClinic.entity.Appointment;
import com.dentalClinic.exceptions.ErrorRequestException;
import com.dentalClinic.exceptions.NotFoundException;

import java.util.List;
import java.util.Set;

public interface AppointmentService {
    Appointment appointmentRecord(Appointment appointment) throws ErrorRequestException;
    Appointment updateAppointment(Appointment appointment);
    Appointment findAppointmentById(Long id);
    Set<AppointmentDTO> searchAllAppointments();
    void removeAppointment(Long id) throws NotFoundException;
    List<Appointment> getAppointmentsByPatientName(String patientName);
}
