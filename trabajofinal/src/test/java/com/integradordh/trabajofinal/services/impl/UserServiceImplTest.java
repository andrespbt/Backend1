package com.integradordh.trabajofinal.services.impl;

import com.integradordh.trabajofinal.models.dto.UserDTO;
import com.integradordh.trabajofinal.services.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    IUserService userService;

    public void createInstance(){
        UserDTO userDTO = new UserDTO("Andres", "Poblete");
        UserDTO userDTO1 = new UserDTO("Fernando", "Ramirez");
        UserDTO userDTO2 = new UserDTO("Lucas", "Galvan");
        userService.saveUser(userDTO);
        userService.saveUser(userDTO1);
        userService.saveUser(userDTO2);

    }

    @Test
    void saveUser() {
        if(userService.searchAllUsers().size() == 0){
            createInstance();
        }
        assertNotNull(userService.searchUserById(1L));
    }

    @Test
    void searchById() {
        if(userService.searchAllUsers().size() == 0){
            createInstance();
        }
        assertEquals(userService.searchUserById(1L).getName(),"Andres");
    }

    @Test
    void updateUser() {
        if(userService.searchAllUsers().size() == 0){
            createInstance();
        }
        UserDTO userDTO1 = new UserDTO(1L, "Pablo", "Moreno");
        userService.updateUser(userDTO1);
        assertEquals(userService.searchUserById(1L).getLastName(), "Moreno");
    }

    @Test
    void deleteUserById() {
        if(userService.searchAllUsers().size() == 0){
            createInstance();
        }
        userService.deleteUserById(1L);
        assertNull(userService.searchUserById(1L));
    }

    @Test
    void searchAll() {
        if(userService.searchAllUsers().size() == 0){
            createInstance();
        }
        assertTrue(userService.searchAllUsers().size() >= 3);


    }
}