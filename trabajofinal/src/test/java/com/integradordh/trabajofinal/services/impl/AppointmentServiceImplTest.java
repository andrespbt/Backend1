package com.integradordh.trabajofinal.services.impl;

import com.integradordh.trabajofinal.models.dto.AppointmentDTO;
import com.integradordh.trabajofinal.models.dto.DentistDTO;
import com.integradordh.trabajofinal.models.dto.PatientDTO;
import com.integradordh.trabajofinal.services.IAppointmentService;
import com.integradordh.trabajofinal.services.IDentistService;
import com.integradordh.trabajofinal.services.IPatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentServiceImplTest {

    @Autowired
    IAppointmentService appointmentService;

    @Autowired
    IDentistService dentistService;

    @Autowired
    IPatientService patientService;

    @Test
    void saveAppointment() {

        LocalTime time = LocalTime.now();
        DentistDTO dentistDTO = new DentistDTO("Ruben","Lazzaro","14123");
        dentistService.saveDentist(dentistDTO);

        Date date = new Date(122, Calendar.NOVEMBER,25);
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = DateFor.format(date);
        PatientDTO patientDTO = new PatientDTO("Andres","Poblete","38416140",date);
        patientService.savePatient(patientDTO);

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setPatient(patientService.searchPatientByNationalId("38416140"));
        appointmentDTO.setDentist(dentistService.searchDentistByLicenseNumber("14123"));
        appointmentDTO.setDate(dateString);
        appointmentDTO.setTime(time);

        appointmentService.saveAppointment(appointmentDTO);

        assertEquals("Lazzaro",appointmentService.searchAppointmentById(1L).getDentist().getLastName());

    }

    @Test
    void searchAppointmentById() {
        LocalTime time = LocalTime.now();
        DentistDTO dentistDTO = new DentistDTO("Ruben","Lazzaro","14123");

        dentistService.saveDentist(dentistDTO);

        Date date = new Date(122, Calendar.NOVEMBER,25);
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = DateFor.format(date);
        PatientDTO patientDTO = new PatientDTO("Andres","Poblete","38416140",date);

        patientService.savePatient(patientDTO);
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setPatient(patientService.searchPatientByNationalId("38416140"));
        appointmentDTO.setDentist(dentistService.searchDentistByLicenseNumber("14123"));
        appointmentDTO.setDate(dateString);
        appointmentDTO.setTime(time);

        appointmentService.saveAppointment(appointmentDTO);
        assertNotNull(appointmentService.searchAppointmentById(1L));
    }

    @Test
    void searchAppointmentsByDentistLicense() {
        LocalTime time = LocalTime.now();
        DentistDTO dentistDTO = new DentistDTO("Ruben","Lazzaro","14123");
        dentistService.saveDentist(dentistDTO);

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

        assertTrue(appointmentService.searchAppointmentsByDentistLicense("14123").size() >= 3);

    }

    @Test
    void searchAppointmentsByPatientNationalId() {

        DentistDTO dentistDTO = new DentistDTO(1L,"Mariana","Fernandez","14123");
        DentistDTO dentistDTO1 = new DentistDTO(2L,"Pablo","Perez","44213");
        DentistDTO dentistDTO2 = new DentistDTO(3L,"Marcos","Acuña","11231");
        dentistService.saveDentist(dentistDTO);
        dentistService.saveDentist(dentistDTO1);
        dentistService.saveDentist(dentistDTO2);

        LocalTime time = LocalTime.now();
        Date date = new Date(122, Calendar.NOVEMBER,25);
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = DateFor.format(date);
        PatientDTO patientDTO = new PatientDTO("Andres","Poblete","38416140",date);
        patientService.savePatient(patientDTO);

        AppointmentDTO appointmentDTO = new AppointmentDTO(dentistService.searchDentistByLicenseNumber("14123"), patientService.searchPatientByNationalId("38416140"), dateString, time);
        AppointmentDTO appointmentDTO1 = new AppointmentDTO(dentistService.searchDentistByLicenseNumber("44213"), patientService.searchPatientByNationalId("38416140"), dateString, time);
        AppointmentDTO appointmentDTO2 = new AppointmentDTO(dentistService.searchDentistByLicenseNumber("11231"), patientService.searchPatientByNationalId("38416140"), dateString, time);


        appointmentService.saveAppointment(appointmentDTO);
        appointmentService.saveAppointment(appointmentDTO1);
        appointmentService.saveAppointment(appointmentDTO2);


        assertTrue(appointmentService.searchAppointmentsByPatientNationalId("38416140").size() >= 3);

    }

    @Test
    void updateAppointment() {

        DentistDTO dentistDTO = new DentistDTO(1L,"Mariana","Fernandez","14123");
        DentistDTO dentistDTO1 = new DentistDTO(2L,"Pablo","Perez","44213");
        dentistService.saveDentist(dentistDTO);
        dentistService.saveDentist(dentistDTO1);


        LocalTime time = LocalTime.now();
        Date date = new Date(122, Calendar.NOVEMBER,25);
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = DateFor.format(date);
        PatientDTO patientDTO = new PatientDTO("Andres","Poblete","38416140",date);
        patientService.savePatient(patientDTO);

        AppointmentDTO appointmentDTO = new AppointmentDTO(dentistService.searchDentistByLicenseNumber("14123"), patientService.searchPatientByNationalId("38416140"), dateString, time);

        appointmentService.saveAppointment(appointmentDTO);

        appointmentDTO.setId(1L);
        appointmentDTO.setDentist(dentistDTO1);


        appointmentService.updateAppointment(appointmentDTO);

        assertEquals("44213", appointmentService.searchAppointmentById(1L).getDentist().getLicenseNumber());
    }

    @Test
    void deleteAppointment() {
        DentistDTO dentistDTO = new DentistDTO(1L,"Mariana","Fernandez","14123");
        dentistService.saveDentist(dentistDTO);

        LocalTime time = LocalTime.now();
        Date date = new Date(122, Calendar.NOVEMBER,25);
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = DateFor.format(date);
        PatientDTO patientDTO = new PatientDTO("Andres","Poblete","38416140",date);
        patientService.savePatient(patientDTO);

        AppointmentDTO appointmentDTO = new AppointmentDTO(dentistService.searchDentistByLicenseNumber("14123"), patientService.searchPatientByNationalId("38416140"), dateString, time);

        appointmentService.saveAppointment(appointmentDTO);

        appointmentService.deleteAppointment(1L);

        assertNull(appointmentService.searchAppointmentById(1L));

    }

    @Test
    void searchAllAppointments() {

        DentistDTO dentistDTO = new DentistDTO(1L,"Mariana","Fernandez","14123");
        DentistDTO dentistDTO1 = new DentistDTO(2L,"Pablo","Perez","44213");
        DentistDTO dentistDTO2 = new DentistDTO(3L,"Marcos","Acuña","11231");
        dentistService.saveDentist(dentistDTO);
        dentistService.saveDentist(dentistDTO1);
        dentistService.saveDentist(dentistDTO2);

        LocalTime time = LocalTime.now();
        Date date = new Date(122, Calendar.NOVEMBER,25);
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = DateFor.format(date);
        PatientDTO patientDTO = new PatientDTO("Andres","Poblete","38416140",date);
        patientService.savePatient(patientDTO);

        AppointmentDTO appointmentDTO = new AppointmentDTO(dentistService.searchDentistByLicenseNumber("14123"), patientService.searchPatientByNationalId("38416140"), dateString, time);
        AppointmentDTO appointmentDTO1 = new AppointmentDTO(dentistService.searchDentistByLicenseNumber("44213"), patientService.searchPatientByNationalId("38416140"), dateString, time);
        AppointmentDTO appointmentDTO2 = new AppointmentDTO(dentistService.searchDentistByLicenseNumber("11231"), patientService.searchPatientByNationalId("38416140"), dateString, time);

        appointmentService.saveAppointment(appointmentDTO);
        appointmentService.saveAppointment(appointmentDTO1);
        appointmentService.saveAppointment(appointmentDTO2);


        assertTrue(appointmentService.searchAllAppointments().size() >= 3);


    }
}