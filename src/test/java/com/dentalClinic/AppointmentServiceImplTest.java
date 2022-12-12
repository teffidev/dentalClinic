package com.dentalClinic;

import com.dentalClinic.dto.AppointmentDTO;
import com.dentalClinic.entity.Appointment;
import com.dentalClinic.entity.Dentist;
import com.dentalClinic.entity.Patient;
import com.dentalClinic.exceptions.ErrorRequestException;
import com.dentalClinic.repository.AppointmentRepository;
import com.dentalClinic.service.impl.AppointmentServiceImpl;
import com.dentalClinic.service.impl.DentistServiceImpl;
import com.dentalClinic.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;
import java.util.Set;

/*After each Test Database context is reset*/
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class AppointmentServiceImplTest {

    private final AppointmentServiceImpl appointmentService;
    private final AppointmentRepository appointmentRepository;
    private final DentistServiceImpl dentistService;
    private final PatientServiceImpl patientService;

    @Autowired
    public AppointmentServiceImplTest(AppointmentServiceImpl appointmentService, AppointmentRepository appointmentRepository, DentistServiceImpl dentistService, PatientServiceImpl patientService) {
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
        this.dentistService = dentistService;
        this.patientService = patientService;
    }

    @BeforeEach
    public void createAppointments() throws ErrorRequestException {
        /*Create Dentists and Patients before we can add them to the Appointment's constructor*/

        Dentist firstDentist = new Dentist("Lorena", "Perez", "LP123");
        dentistService.dentistRecord(firstDentist);
        Dentist secondDentist = new Dentist("Marco", "Martinez", "MM123");
        dentistService.dentistRecord(secondDentist);
        Dentist thirdDentist = new Dentist("Carol", "Pazote", "CP123");
        dentistService.dentistRecord(thirdDentist);
        Dentist fourthDentist = new Dentist("Pablo", "Lucca", "PL123");
        dentistService.dentistRecord(fourthDentist);

        Patient firstPatient = new Patient("Olga", "Ortiz", "Calle 10 Avenida", "DNI897456", new Date());
        patientService.patientRecord(firstPatient);
        Patient secondPatient = new Patient("Patricia", "Parra", "Avenida Fenix", "DNI365412", new Date());
        patientService.patientRecord(secondPatient);
        Patient thirdPatient = new Patient("Dan", "Linares", "12 street Avenida", "DNI8700", new Date());
        patientService.patientRecord(thirdPatient);
        Patient fourthPatient = new Patient("Rafael", "Hoyos", "Street Olaya", "DNI00056", new Date());
        patientService.patientRecord(fourthPatient);

        Appointment firstAppointment = new Appointment(new Date(), firstDentist, firstPatient);
        appointmentRepository.save(firstAppointment);
        Appointment secondAppointment = new Appointment(new Date(), secondDentist, secondPatient);
        appointmentRepository.save(secondAppointment);
        Appointment thirdAppointment = new Appointment(new Date(), thirdDentist, thirdPatient);
        appointmentRepository.save(thirdAppointment);
        Appointment fourthAppointment = new Appointment(new Date(), fourthDentist, fourthPatient);
        appointmentRepository.save(fourthAppointment);
    }

    @Test
    public void getAllAppointmentsTest(){
        Set<AppointmentDTO> appointments = appointmentService.searchAllAppointments();
        Assertions.assertFalse(appointments.isEmpty());
        Assertions.assertEquals(4, appointments.size());
    }

}
