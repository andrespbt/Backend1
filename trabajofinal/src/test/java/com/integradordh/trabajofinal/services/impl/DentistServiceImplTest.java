package com.integradordh.trabajofinal.services.impl;

import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.exceptions.ResourceNotFoundException;
import com.integradordh.trabajofinal.models.Dentist;
import com.integradordh.trabajofinal.services.IDentistService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DentistServiceImplTest {

    @Autowired
    IDentistService dentistService;

    public void createInstance() throws BadRequestException {
        Dentist dentist = new Dentist("Andres","Poblete","14123");
        Dentist dentist1 = new Dentist("Pablo","Perez","44213");
        Dentist dentist2 = new Dentist("Marcos","Acuña","11231");
        dentistService.saveDentist(dentist);
        dentistService.saveDentist(dentist1);
        dentistService.saveDentist(dentist2);

    }

    @Test
    @Order(1)
    void saveDentist() throws BadRequestException, ResourceNotFoundException {
        if(dentistService.searchAllDentists().size() == 0){
            createInstance();
        }
        assertEquals("Poblete", dentistService.searchDentistByLicenseNumber("14123").getLastName());
    }

    @Test
    @Order(2)
    void searchDentistById() throws BadRequestException, ResourceNotFoundException {
        if(dentistService.searchAllDentists().size() == 0){
            createInstance();
        }
        assertNotNull(dentistService.searchDentistById(1L));
    }

    @Test
    @Order(3)
    void searchDentistByLicenseNumber() throws BadRequestException, ResourceNotFoundException {
        if(dentistService.searchAllDentists().size() == 0){
            createInstance();
        }
        assertEquals("Andres", dentistService.searchDentistByLicenseNumber("14123").getName());
    }

    @Test
    @Order(5)
    void updateDentist() throws BadRequestException, ResourceNotFoundException {

        if(dentistService.searchAllDentists().size() == 0){
            createInstance();
        }
        dentistService.updateDentist(new Dentist(1L,"Andres", "Moreno", "14123"));
        assertEquals("Moreno", dentistService.searchDentistById(1L).getLastName());
    }

    @Test
    @Order(6)
    void deleteDentistById() throws BadRequestException, ResourceNotFoundException {

        if(dentistService.searchAllDentists().size() == 0){
            createInstance();
        }
        dentistService.deleteDentistById(1L);
        assertThrows(ResourceNotFoundException.class, () -> dentistService.searchDentistById(1L));
    }

    @Test
    @Order(4)
    void searchAllDentists() throws BadRequestException {
        if(dentistService.searchAllDentists().size() == 0){
            createInstance();
        }
        assertTrue(dentistService.searchAllDentists().size() >= 3);
    }
}