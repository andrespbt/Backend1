package com.trabajointegrador.trabajointegradordh.controller;

import com.trabajointegrador.trabajointegradordh.model.Dentist;
import com.trabajointegrador.trabajointegradordh.model.User;
import com.trabajointegrador.trabajointegradordh.services.DentistService;
import com.trabajointegrador.trabajointegradordh.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class DentistController {

    @Autowired
    private DentistService dentistService;

    @GetMapping("/dentist")
    public User getDentistHandler(@RequestParam("id") Long id) throws SQLException {

        return dentistService.search(id);
    }

    @GetMapping("/dentists")
    public List<Dentist> getAllDentistsHandler() throws SQLException {

        return dentistService.searchAll();

    }
}
