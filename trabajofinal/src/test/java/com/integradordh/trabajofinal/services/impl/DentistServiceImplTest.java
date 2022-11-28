package com.integradordh.trabajofinal.services.impl;

import com.integradordh.trabajofinal.models.dto.DentistDTO;
import com.integradordh.trabajofinal.services.IDentistService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DentistServiceImplTest {

    @Autowired
    IDentistService dentistService;

    @Test
    void saveDentist() {
        DentistDTO dentistDTO = new DentistDTO("Andres","Poblete","14123");
        dentistService.saveDentist(dentistDTO);
        assertEquals("Poblete", dentistService.searchDentistById(1L).getLastName());
    }

    @Test
    void searchDentistById() {
        DentistDTO dentistDTO = new DentistDTO("Andres","Poblete","14123");
        dentistService.saveDentist(dentistDTO);
        Long idToSearch = dentistService.searchDentistByLicenseNumber("14123").getId();
        assertNotNull(dentistService.searchDentistById(idToSearch));
    }

    @Test
    void searchDentistByLicenseNumber() {
        DentistDTO dentistDTO = new DentistDTO("Andres","Poblete","14123");
        dentistService.saveDentist(dentistDTO);
        assertEquals("Andres", dentistService.searchDentistByLicenseNumber("14123").getName());
    }

    @Test
    void updateDentist() {
        dentistService.saveDentist(new DentistDTO("Andres","Poblete","14123"));
        dentistService.updateDentist(new DentistDTO(1L,"Andres", "Moreno", "14123"));
        assertEquals("Moreno", dentistService.searchDentistById(1L).getLastName());
    }

    @Test
    void deleteDentistById() {

        DentistDTO dentistDTO = new DentistDTO(1L,"Andres","Poblete","14123");
        dentistService.saveDentist(dentistDTO);
        dentistService.deleteDentistById(1L);
        assertNull(dentistService.searchDentistById(1L));
    }

    @Test
    void searchAllDentists() {
        DentistDTO dentistDTO = new DentistDTO("Andres","Poblete","14123");
        DentistDTO dentistDTO1 = new DentistDTO("Pablo","Perez","44213");
        DentistDTO dentistDTO2 = new DentistDTO("Marcos","Acuña","11231");
        dentistService.saveDentist(dentistDTO);
        dentistService.saveDentist(dentistDTO1);
        dentistService.saveDentist(dentistDTO2);

        assertTrue(dentistService.searchAllDentists().size() >= 3);
    }
}