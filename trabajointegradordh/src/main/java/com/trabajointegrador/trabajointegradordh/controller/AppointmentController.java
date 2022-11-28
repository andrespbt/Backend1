package com.trabajointegrador.trabajointegradordh.controller;

import com.trabajointegrador.trabajointegradordh.model.Appointment;
import com.trabajointegrador.trabajointegradordh.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointment")
    public Appointment getAppointmentHandler(@RequestParam("id") Long id) throws SQLException {

        return appointmentService.search(id);
    }

    @GetMapping("/appointments")
    public List<Appointment> getAllAppointmentsHandler() throws SQLException {

        return appointmentService.searchAll();

    }
}
