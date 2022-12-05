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
        ResponseEntity<String> response = null;
        try{
            UserDTO user = userService.searchUserById(id);
            response = ResponseEntity.status(HttpStatus.OK).body(user.toString());
        }catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + id + " doesn't exists");
            e.printStackTrace();
        }
            return response;
    }

    @PatchMapping("/update")
    public ResponseEntity<?> modifyUser(@RequestBody UserDTO userDTO) throws ResourceNotFoundException, BadRequestException {
        ResponseEntity<String> response = null;
            try {
                userService.updateUser(userDTO);
                response = ResponseEntity.status(HttpStatus.OK).body("User modified " + userDTO.toString());

            }catch (Exception e){
               e.printStackTrace();
            }
        return ResponseEntity.ok().body("User updated succesfully" + userService.searchUserById(userDTO.getId()).toString());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) throws ResourceNotFoundException {
        String userInfo = userService.searchUserById(id).toString();
        userService.deleteUserById(id);
        return ResponseEntity.ok().body("User deleted succesfully" + userInfo);
    }

    @GetMapping
    public Set<UserDTO> searchAllUsers() {
        return userService.searchAllUsers();
    }


}
