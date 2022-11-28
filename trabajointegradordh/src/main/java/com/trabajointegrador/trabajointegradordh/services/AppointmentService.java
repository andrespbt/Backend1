package com.trabajointegrador.trabajointegradordh.services;

import com.trabajointegrador.trabajointegradordh.model.Appointment;
import com.trabajointegrador.trabajointegradordh.model.Dentist;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentService {

    Appointment save(Appointment appointment) throws SQLException;

    void delete(Long id) throws SQLException;

    Appointment search(Long id) throws SQLException;

    List<Appointment> searchAll() throws SQLException;
}

