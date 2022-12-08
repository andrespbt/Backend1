package com.integradordh.trabajofinal.services.impl;

import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.exceptions.ResourceNotFoundException;
import com.integradordh.trabajofinal.models.Patient;
import com.integradordh.trabajofinal.services.IPatientService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PatientServiceImplTest {

    @Autowired
    IPatientService patientService;

    public void createInstance() throws BadRequestException {
        LocalDate date = LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        Patient patient = new Patient("Andres","Poblete","38416140", date, "Mendoza");
        Patient patient1 = new Patient("Pablo","Guevara","11453231", date, "San Juan");
        Patient patient2 = new Patient("Marcos","Mitre","5432123", date, "Entre Rios");
        patientService.savePatient(patient);
        patientService.savePatient(patient1);
        patientService.savePatient(patient2);
    }


    @Test
    @Order(1)
    void savePatient() throws BadRequestException, ResourceNotFoundException {
        if(patientService.searchAllPatients().size() == 0){
            createInstance();
        }
        assertNotNull(patientService.searchPatientById(1L));
    }

    @Test
    void searchPatientById() throws BadRequestException, ResourceNotFoundException {

        if(patientService.searchAllPatients().size() == 0){
            createInstance();
        }
        assertEquals("38416140", patientService.searchPatientById(1L).getNationalId());

    }

    @Test
    void searchPatientByNationalId() throws BadRequestException, ResourceNotFoundException {
        if(patientService.searchAllPatients().size() == 0){
            createInstance();
        }
        assertEquals("Andres", patientService.searchPatientByNationalId("38416140").getName());

    }

    @Test
    @Order(2)
    void updatePatient() throws BadRequestException, ResourceNotFoundException {

        if(patientService.searchAllPatients().size() == 0){
            createInstance();
        }
        LocalDate date = LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        Patient patient = new Patient(1L,"Andres","Ramirez","38416140",date, "Cordoba");
        patientService.updatePatient(patient);
        assertEquals("Ramirez", patientService.searchPatientByNationalId("38416140").getLastName());
    }

    @Test
    void deletePatientById() throws BadRequestException, ResourceNotFoundException {
        if(patientService.searchAllPatients().size() == 0){
            createInstance();
        }
        patientService.deletePatientById(1L);
        assertThrows(ResourceNotFoundException.class, () -> patientService.searchPatientById(1L));
    }

    @Test
    void searchAllPatients() throws BadRequestException {

        if(patientService.searchAllPatients().size() == 0){
            createInstance();
        }
        assertTrue(patientService.searchAllPatients().size() >= 3);

    }
}