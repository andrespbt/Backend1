package com.trabajointegrador.trabajointegradordh.controller;
import com.trabajointegrador.trabajointegradordh.model.Patient;
import com.trabajointegrador.trabajointegradordh.model.User;
import com.trabajointegrador.trabajointegradordh.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patient")
    public Patient getPatientHandler(@RequestParam("id") Long id) throws SQLException {

        return patientService.search(id);
    }

    @GetMapping("/patients")
    public List<Patient> getAllUsersHandler() throws SQLException {

        return patientService.searchAll();

    }
}
