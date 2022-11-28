package com.trabajointegrador.trabajointegradordh.services;

import com.trabajointegrador.trabajointegradordh.model.Patient;
import com.trabajointegrador.trabajointegradordh.model.User;

import java.sql.SQLException;
import java.util.List;

public interface PatientService {

    Patient save(Patient patient) throws SQLException;

    void delete(Long id) throws SQLException;

    Patient search(Long id) throws SQLException;

    List<Patient> searchAll() throws SQLException;
}
