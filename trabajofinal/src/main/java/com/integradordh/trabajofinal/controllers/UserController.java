package com.integradordh.trabajofinal.controllers;

import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.exceptions.ResourceNotFoundException;
import com.integradordh.trabajofinal.models.dto.UserDTO;
import com.integradordh.trabajofinal.models.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserService userService;


    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) throws BadRequestException {
        ResponseEntity<String> response;
        userService.saveUser(userDTO);

        response = ResponseEntity.status(HttpStatus.CREATED).body("User saved succesfully" + userDTO.toString());
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<?> response = null;
        try{
            UserDTO user = userService.searchUserById(id);
            response = ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (ResourceNotFoundException e) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + id + " doesn't exists");
            e.printStackTrace();
        }
            return response;
    }

    @PatchMapping("/update")
    public ResponseEntity<?> modifyUser(@RequestBody UserDTO userDTO) throws ResourceNotFoundException, BadRequestException {
        ResponseEntity<?> response = null;
            try {
                userService.updateUser(userDTO);
                UserDTO userUpdated = userService.searchUserById(userDTO.getId());
                response = ResponseEntity.status(HttpStatus.OK).body(userUpdated);

            }catch (Exception e){
               e.printStackTrace();
               response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User can't be modified.");
            }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<?> response = null;
        try {
            UserDTO userDeleted = userService.searchUserById(id);
            userService.deleteUserById(id);
            response = ResponseEntity.ok().body(userDeleted);
        }catch (Exception e) {
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User can't be deleted.");
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<?> searchAllUsers() throws ResourceNotFoundException {
        ResponseEntity<?> response = null;
        try {
            Set<UserDTO> users = userService.searchAllUsers();
            response = ResponseEntity.ok().body(users);
        }catch (Exception e) {
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Users not found.");
        }
        return response;
    }


}
