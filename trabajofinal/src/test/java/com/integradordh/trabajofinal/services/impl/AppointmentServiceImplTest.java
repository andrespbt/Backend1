package com.integradordh.trabajofinal.services.impl;

import com.integradordh.trabajofinal.models.dto.AppointmentDTO;
import com.integradordh.trabajofinal.models.dto.DentistDTO;
import com.integradordh.trabajofinal.models.dto.PatientDTO;
import com.integradordh.trabajofinal.services.IAppointmentService;
import com.integradordh.trabajofinal.services.IDentistService;
import com.integradordh.trabajofinal.services.IPatientService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

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

    public void createInstance(){
        LocalTime time = LocalTime.now();
        DentistDTO dentistDTO = new DentistDTO("Ruben","Lazzaro","14123");
        DentistDTO dentistDTO1 = new DentistDTO("Mario","Jaurez","123456");
        DentistDTO dentistDTO2 = new DentistDTO("Pablo","Martinez","23456");
        dentistService.saveDentist(dentistDTO);
        dentistService.saveDentist(dentistDTO1);
        dentistService.saveDentist(dentistDTO2);

        Date date = new Date(122, Calendar.NOVEMBER,25);
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = DateFor.format(date);
        PatientDTO patientDTO = new PatientDTO("Andres","Poblete","38416140",date);
        PatientDTO patientDTO1 = new PatientDTO("Pablo","Guevara","11453231",date);
        PatientDTO patientDTO2 = new PatientDTO("Marcos","Mitre","5432123",date);
        patientService.savePatient(patientDTO);
        patientService.savePatient(patientDTO1);
        patientService.savePatient(patientDTO2);

        AppointmentDTO appointmentDTO = new AppointmentDTO(dentistService.searchDentistByLicenseNumber("14123"), patientService.searchPatientByNationalId("38416140"), dateString, time);
        AppointmentDTO appointmentDTO1 = new AppointmentDTO(dentistService.searchDentistByLicenseNumber("14123"), patientService.searchPatientByNationalId("11453231"), dateString, time);
        AppointmentDTO appointmentDTO2 = new AppointmentDTO(dentistService.searchDentistByLicenseNumber("14123"), patientService.searchPatientByNationalId("5432123"), dateString, time);

        appointmentService.saveAppointment(appointmentDTO);
        appointmentService.saveAppointment(appointmentDTO1);
        appointmentService.saveAppointment(appointmentDTO2);

    }

    @Test
    @Order(1)
    void saveAppointment() {

        if(appointmentService.searchAppointmentById(1L) == null){
            createInstance();
        }

        assertEquals("Lazzaro",appointmentService.searchAppointmentById(1L).getDentist().getLastName());

    }

    @Test
    @Order(3)
    void searchAppointmentById() {

        if(appointmentService.searchAppointmentsByDentistLicense("14123").size() == 0){
            createInstance();
        }
        assertNotNull(appointmentService.searchAppointmentById(1L));
    }

    @Test
    @Order(4)
    void searchAppointmentsByDentistLicense() {
        if(appointmentService.searchAllAppointments().size() < 3){
            createInstance();
        }
        assertTrue(appointmentService.searchAppointmentsByDentistLicense("14123").size() >= 3);

    }

    @Test
    @Order(5)
    void searchAppointmentsByPatientNationalId() {
        if(appointmentService.searchAllAppointments().size() == 0){
            createInstance();
        }
        assertTrue(appointmentService.searchAppointmentsByPatientNationalId("38416140").size() >= 1);

    }

    @Test
    @Order(6)
    void updateAppointment() {

        if(appointmentService.searchAllAppointments().size() == 0){
            createInstance();
        }
        AppointmentDTO appointmentDTOUpdated = appointmentService.searchAppointmentById(1L);
        DentistDTO dentistDTO1 = dentistService.searchDentistByLicenseNumber("123456");
        appointmentDTOUpdated.setDentist(dentistDTO1);
        appointmentDTOUpdated.setId(1L);
        appointmentService.updateAppointment(appointmentDTOUpdated);

        assertEquals("123456", appointmentService.searchAppointmentById(1L).getDentist().getLicenseNumber());
    }

    @Test
    @Order(7)
    void deleteAppointment() {
        if(appointmentService.searchAllAppointments().size() == 0){
            createInstance();
        }
        appointmentService.deleteAppointment(1L);

        assertNull(appointmentService.searchAppointmentById(1L));

    }

    @Test
    @Order(2)
    void searchAllAppointments() {

       if(appointmentService.searchAllAppointments().size() == 0){
           createInstance();
       }


        assertTrue(appointmentService.searchAllAppointments().size() >= 3);


    }
}