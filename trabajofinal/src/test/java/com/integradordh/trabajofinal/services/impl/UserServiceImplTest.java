package com.integradordh.trabajofinal.services.impl;

import com.integradordh.trabajofinal.models.dto.UserDTO;
import com.integradordh.trabajofinal.services.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    IUserService userService;

    @BeforeEach

    @Test
    void saveUser() {
        UserDTO userDTO = new UserDTO("Andres", "Poblete");
        userService.saveUser(userDTO);
        UserDTO userDTO1 = userService.searchUserById(1L);
        assertNotNull(userDTO1);
    }

    @Test
    void searchById() {
        UserDTO userDTO = new UserDTO("Andres", "Poblete");
        userService.saveUser(userDTO);
        UserDTO userDTO1 = userService.searchUserById(1L);
        assertEquals(userDTO1.getName(),"Andres");
    }

    @Test
    void updateUser() {
        UserDTO userDTO = new UserDTO("Andres", "Poblete");
        userService.saveUser(userDTO);
        UserDTO userDTO1 = new UserDTO(1L, "Pablo", "Moreno");
        userService.updateUser(userDTO1);
        UserDTO finalUser = userService.searchUserById(1L);
        assertEquals(finalUser.getLastName(), "Moreno");
    }

    @Test
    void deleteUserById() {
        UserDTO userDTO = new UserDTO("Andres", "Poblete");
        userService.saveUser(userDTO);
        userService.deleteUserById(1L);
        assertNull(userService.searchUserById(1L));
    }

    @Test
    void searchAll() {
        UserDTO userDTO = new UserDTO("Andres", "Poblete");
        UserDTO userDTO1 = new UserDTO("Fernando", "Ramirez");
        UserDTO userDTO2 = new UserDTO("Lucas", "Galvan");
        userService.saveUser(userDTO);
        userService.saveUser(userDTO1);
        userService.saveUser(userDTO2);

        assertTrue(userService.searchAllUsers().size() >= 3);


    }
}