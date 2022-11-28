package com.trabajointegrador.trabajointegradordh.services;

import com.trabajointegrador.trabajointegradordh.model.Dentist;
import com.trabajointegrador.trabajointegradordh.model.User;

import java.sql.SQLException;
import java.util.List;

public interface DentistService {

    Dentist save(Dentist dentist) throws SQLException;

    void delete(Long id) throws SQLException;

    Dentist search(Long id) throws SQLException;

    List<Dentist> searchAll() throws SQLException;
}
