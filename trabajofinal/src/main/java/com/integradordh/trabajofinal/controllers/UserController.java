package com.integradordh.trabajofinal.controllers;

import com.integradordh.trabajofinal.models.dto.UserDTO;
import com.integradordh.trabajofinal.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserService userService;


    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
        return ResponseEntity.ok().body("User saved succesfully" + userDTO.toString());
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id){
        return userService.searchUserById(id);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> modifyUser(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return ResponseEntity.ok().body("User updated succesfully" + userService.searchUserById(userDTO.getId()).toString());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        String userInfo = userService.searchUserById(id).toString();
        userService.deleteUserById(id);
        return ResponseEntity.ok().body("User deleted succesfully" + userInfo);
    }

    @GetMapping
    public Set<UserDTO> searchAllUsers() {
        return userService.searchAllUsers();
    }

}
