package com.trabajointegrador.trabajointegradordh.services;

import com.trabajointegrador.trabajointegradordh.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    User save(User user) throws SQLException;

    void delete(Long id) throws SQLException;

    User search(Long id) throws SQLException;

    List<User> searchAll() throws SQLException;
}
