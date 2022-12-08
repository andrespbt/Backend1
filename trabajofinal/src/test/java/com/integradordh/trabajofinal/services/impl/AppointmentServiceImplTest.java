package com.integradordh.trabajofinal.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.exceptions.ResourceNotFoundException;
import com.integradordh.trabajofinal.models.Appointment;
import com.integradordh.trabajofinal.models.Dentist;
import com.integradordh.trabajofinal.models.Patient;
import com.integradordh.trabajofinal.models.dto.AppointmentDTOComplete;
import com.integradordh.trabajofinal.models.dto.DentistDTOComplete;
import com.integradordh.trabajofinal.services.IAppointmentService;
import com.integradordh.trabajofinal.services.IDentistService;
import com.integradordh.trabajofinal.services.IPatientService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AppointmentServiceImplTest {

    @Autowired
    IAppointmentService appointmentService;

    @Autowired
    IDentistService dentistService;

    @Autowired
    IPatientService patientService;

    @Autowired
    ObjectMapper objectMapper;

    public void createDentistAndPatient() throws BadRequestException, ResourceNotFoundException {
        Dentist dentist = new Dentist("Andres","Poblete","14123");
        Dentist dentist1 = new Dentist("Pablo","Perez","44213");
        Dentist dentist2 = new Dentist("Marcos","Acuña","11231");
        dentistService.saveDentist(dentist);
        dentistService.saveDentist(dentist1);
        dentistService.saveDentist(dentist2);

        LocalDate date1 = LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        Patient patient = new Patient("Andres","Poblete","38416140", date1, "Mendoza");
        Patient patient1 = new Patient("Pablo","Guevara","11453231", date1, "San Juan");
        Patient patient2 = new Patient("Marcos","Mitre","5432123", date1, "Entre Rios");
        patientService.savePatient(patient);
        patientService.savePatient(patient1);
        patientService.savePatient(patient2);


    }

    public void createInstance() throws BadRequestException, ResourceNotFoundException {
        LocalTime time = LocalTime.now();
        LocalDate date2 = LocalDate.of(2022,12, 15);
        Dentist dentist1 = objectMapper.convertValue(dentistService.searchDentistDTOCompleteByLicenseNumber("14123"), Dentist.class);
        Dentist dentist2 = objectMapper.convertValue(dentistService.searchDentistDTOCompleteByLicenseNumber("44213"), Dentist.class);
        Dentist dentist3 = objectMapper.convertValue(dentistService.searchDentistDTOCompleteByLicenseNumber("11231"), Dentist.class);

        Patient patient1 = objectMapper.convertValue(patientService.searchPatientCompleteByNationalId("38416140"),Patient.class);
        Patient patient2 = objectMapper.convertValue(patientService.searchPatientCompleteByNationalId("11453231"), Patient.class);
        Patient patient3 = objectMapper.convertValue(patientService.searchPatientCompleteByNationalId("5432123"), Patient.class);

        Appointment appointment = new Appointment(dentist1, patient1, date2, time);
        Appointment appointment1 = new Appointment(dentist2, patient2, date2, time);
        Appointment appointment2 = new Appointment(dentist3, patient3, date2, time);
        appointmentService.saveAppointment(appointment);
        appointmentService.saveAppointment(appointment1);
        appointmentService.saveAppointment(appointment2);

    }

    @Test
    @Order(1)
    void saveAppointment() throws BadRequestException, ResourceNotFoundException {

        if(appointmentService.searchAllAppointments().size() == 0){
            createDentistAndPatient();
            createInstance();
        }

        assertEquals("Poblete",appointmentService.searchAppointmentById(1L).getDentist().getLastName());

    }

    @Test
    @Order(3)
    void searchAppointmentById() throws BadRequestException, ResourceNotFoundException {

        if(appointmentService.searchAllAppointments().size() == 0){
            createDentistAndPatient();
            createInstance();
        }
        assertNotNull(appointmentService.searchAppointmentById(1L));
    }

    @Test
    @Order(4)
    void searchAppointmentsByDentistLicense() throws BadRequestException, ResourceNotFoundException {
        if(appointmentService.searchAllAppointments().size() == 0){
            createDentistAndPatient();
            createInstance();
        }
        assertTrue(appointmentService.searchAppointmentsByDentistLicense("14123").size() > 0);

    }

    @Test
    @Order(5)
    void searchAppointmentsByPatientNationalId() throws BadRequestException, ResourceNotFoundException {
        if(appointmentService.searchAllAppointments().size() == 0){
            createDentistAndPatient();
            createInstance();
        }
        assertTrue(appointmentService.searchAppointmentsByPatientNationalId("38416140").size() > 0);

    }

    @Test
    @Order(6)
    void updateAppointment() throws BadRequestException, ResourceNotFoundException {


        if(appointmentService.searchAllAppointments().size() == 0){
            createDentistAndPatient();
            createInstance();
        }
        AppointmentDTOComplete appointmentDTOToUpdate = appointmentService.searchAppointmentDTOCompleteById(1L);
        DentistDTOComplete dentistDTOComplete = dentistService.searchDentistDTOCompleteByLicenseNumber("44213");
        appointmentDTOToUpdate.setDentist(dentistDTOComplete);
        Appointment appointmentUpdated = objectMapper.convertValue(appointmentDTOToUpdate, Appointment.class);
        appointmentService.updateAppointment(appointmentUpdated);

        assertEquals("Perez", appointmentService.searchAppointmentById(1L).getDentist().getLastName());
    }

    @Test
    @Order(7)
    void deleteAppointment() throws BadRequestException, ResourceNotFoundException {
        if(appointmentService.searchAllAppointments().size() == 0){
            createDentistAndPatient();
            createInstance();
        }
        appointmentService.deleteAppointment(1L);

        assertThrows(ResourceNotFoundException.class, () -> appointmentService.searchAppointmentById(1L));

    }

    @Test
    @Order(2)
    void searchAllAppointments() throws BadRequestException, ResourceNotFoundException {

       if(appointmentService.searchAllAppointments().size() == 0){
           createDentistAndPatient();
           createInstance();
       }


        assertTrue(appointmentService.searchAllAppointments().size() >= 3);


    }
}