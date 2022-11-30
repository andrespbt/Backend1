package com.integradordh.trabajofinal.services.impl;

import com.integradordh.trabajofinal.models.dto.PatientDTO;
import com.integradordh.trabajofinal.services.IPatientService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PatientServiceImplTest {

    @Autowired
    IPatientService patientService;

    public void createInstance(){
        Date date = new Date(122, Calendar.NOVEMBER,25);
        PatientDTO patientDTO = new PatientDTO("Andres","Poblete","38416140",date);
        PatientDTO patientDTO1 = new PatientDTO("Pablo","Guevara","11453231",date);
        PatientDTO patientDTO2 = new PatientDTO("Marcos","Mitre","5432123",date);
        patientService.savePatient(patientDTO);
        patientService.savePatient(patientDTO1);
        patientService.savePatient(patientDTO2);
    }


    @Test
    @Order(1)
    void savePatient() {
        if(patientService.searchAllPatients().size() == 0){
            createInstance();
        }
        assertNotNull(patientService.searchPatientById(1L));
    }

    @Test
    void searchPatientById() {

        if(patientService.searchAllPatients().size() == 0){
            createInstance();
        }
        assertEquals("38416140", patientService.searchPatientById(1L).getNationalId());

    }

    @Test
    void searchPatientByNationalId() {
        if(patientService.searchAllPatients().size() == 0){
            createInstance();
        }
        assertEquals("Andres", patientService.searchPatientByNationalId("38416140").getName());

    }

    @Test
    @Order(2)
    void updatePatient() {

        if(patientService.searchAllPatients().size() == 0){
            createInstance();
        }
        PatientDTO patientDTO1 = new PatientDTO(1L,"Andres","Ramirez","38416140","2002", "Avenu");
        patientService.updatePatient(patientDTO1);
        assertEquals("Ramirez", patientService.searchPatientByNationalId("38416140").getLastName());
    }

    @Test
    void deletePatientById() {
        if(patientService.searchAllPatients().size() == 0){
            createInstance();
        }
        patientService.deletePatientById(1L);
        assertNull(patientService.searchPatientById(1L));
    }

    @Test
    void searchAllPatients() {

        if(patientService.searchAllPatients().size() == 0){
            createInstance();
        }
        assertTrue(patientService.searchAllPatients().size() >= 3);

    }
}