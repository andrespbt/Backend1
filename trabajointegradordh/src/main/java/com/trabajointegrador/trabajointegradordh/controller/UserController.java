package com.trabajointegrador.trabajointegradordh.controller;

import com.trabajointegrador.trabajointegradordh.model.User;
import com.trabajointegrador.trabajointegradordh.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User getUserHandler(@RequestParam("id") Long id) throws SQLException {

        return userService.search(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsersHandler() throws SQLException {

        return userService.searchAll();

    }
}
