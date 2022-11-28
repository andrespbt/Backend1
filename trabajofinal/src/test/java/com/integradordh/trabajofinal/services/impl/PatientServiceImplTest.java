package com.integradordh.trabajofinal.services.impl;

import com.integradordh.trabajofinal.models.dto.PatientDTO;
import com.integradordh.trabajofinal.services.IPatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServiceImplTest {

    @Autowired
    IPatientService patientService;


    @Test
    void savePatient() {
        Date date = new Date(122, Calendar.NOVEMBER,25);
        PatientDTO patientDTO = new PatientDTO("Andres","Poblete","38416140",date);
        patientService.savePatient(patientDTO);
        PatientDTO patientDTO1 = patientService.searchPatientById(1L);
        assertNotNull(patientDTO1);
    }

    @Test
    void searchPatientById() {

        Date date = new Date(122, Calendar.NOVEMBER,25);
        PatientDTO patientDTO = new PatientDTO("Andres","Poblete","38416140",date);
        patientService.savePatient(patientDTO);
        PatientDTO patientDTO1 = patientService.searchPatientById(1L);
        assertEquals("38416140", patientDTO1.getNationalId());

    }

    @Test
    void searchPatientByNationalId() {

        Date date = new Date(122, Calendar.NOVEMBER,25);
        PatientDTO patientDTO = new PatientDTO("Andres","Poblete","38416140",date);
        patientService.savePatient(patientDTO);
        PatientDTO patientDTO1 = patientService.searchPatientByNationalId("38416140");
        assertEquals("Andres", patientDTO1.getName());

    }

    @Test
    void updatePatient() {
        Date date = new Date(122, Calendar.NOVEMBER,25);
        PatientDTO patientDTO = new PatientDTO("Andres","Poblete","38416140",date);
        patientService.savePatient(patientDTO);
        patientDTO.setId(1L);
        patientDTO.setLastName("Ramirez");
        patientService.updatePatient(patientDTO);
        assertEquals("Ramirez", patientService.searchPatientById(1L).getLastName());
    }

    @Test
    void deletePatientById() {
        Date date = new Date(122, Calendar.NOVEMBER,25);
        PatientDTO patientDTO = new PatientDTO("Andres","Poblete","38416140",date);
        patientService.savePatient(patientDTO);
        patientService.deletePatientById(1L);
        assertNull(patientService.searchPatientById(1L));
    }

    @Test
    void searchAllPatients() {

        Date date = new Date(122, Calendar.NOVEMBER,25);
        PatientDTO patientDTO = new PatientDTO("Andres","Poblete","38416140",date);
        PatientDTO patientDTO1 = new PatientDTO("Pablo","Guevara","11453231",date);
        PatientDTO patientDTO2 = new PatientDTO("Marcos","Mitre","5432123",date);
        patientService.savePatient(patientDTO);
        patientService.savePatient(patientDTO1);
        patientService.savePatient(patientDTO2);

        assertTrue(patientService.searchAllPatients().size() >= 3);

    }
}