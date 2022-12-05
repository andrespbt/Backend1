package com.integradordh.trabajofinal.services.impl;

import com.integradordh.trabajofinal.models.dto.DentistDTO;
import com.integradordh.trabajofinal.models.services.IDentistService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DentistServiceImplTest {

    @Autowired
    IDentistService dentistService;

    public void createInstance(){
        DentistDTO dentistDTO = new DentistDTO("Andres","Poblete","14123");
        DentistDTO dentistDTO1 = new DentistDTO("Pablo","Perez","44213");
        DentistDTO dentistDTO2 = new DentistDTO("Marcos","Acuña","11231");
        dentistService.saveDentist(dentistDTO);
        dentistService.saveDentist(dentistDTO1);
        dentistService.saveDentist(dentistDTO2);

    }

    @Test
    @Order(1)
    void saveDentist() {
        if(dentistService.searchAllDentists().size() == 0){
            createInstance();
        }
        assertEquals("Poblete", dentistService.searchDentistByLicenseNumber("14123").getLastName());
    }

    @Test
    @Order(2)
    void searchDentistById() {
        if(dentistService.searchAllDentists().size() == 0){
            createInstance();
        }
        assertNotNull(dentistService.searchDentistById(1L));
    }

    @Test
    @Order(3)
    void searchDentistByLicenseNumber() {
        if(dentistService.searchAllDentists().size() == 0){
            createInstance();
        }
        assertEquals("Andres", dentistService.searchDentistByLicenseNumber("14123").getName());
    }

    @Test
    @Order(5)
    void updateDentist() {

        if(dentistService.searchAllDentists().size() == 0){
            createInstance();
        }
        dentistService.updateDentist(new DentistDTO(1L,"Andres", "Moreno", "14123"));
        assertEquals("Moreno", dentistService.searchDentistById(1L).getLastName());
    }

    @Test
    @Order(6)
    void deleteDentistById() {

        if(dentistService.searchAllDentists().size() == 0){
            createInstance();
        }
        dentistService.deleteDentistById(1L);
        assertNull(dentistService.searchDentistById(1L));
    }

    @Test
    @Order(4)
    void searchAllDentists() {
        if(dentistService.searchAllDentists().size() == 0){
            createInstance();
        }
        assertTrue(dentistService.searchAllDentists().size() >= 3);
    }
}