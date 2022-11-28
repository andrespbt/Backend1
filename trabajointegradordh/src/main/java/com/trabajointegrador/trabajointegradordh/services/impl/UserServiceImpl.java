package com.trabajointegrador.trabajointegradordh.services.impl;

import com.trabajointegrador.trabajointegradordh.dao.IDao;
import com.trabajointegrador.trabajointegradordh.dao.impl.UserDAOH2;
import com.trabajointegrador.trabajointegradordh.model.User;
import com.trabajointegrador.trabajointegradordh.services.UserService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final IDao<User> userDao;

    public UserServiceImpl(IDao<User> userDao) {
        this.userDao = userDao;
    }


    @Override
    public User save(User user) throws SQLException {
        userDao.save(user);
        return user;
    }

    @Override
    public void delete(Long id) throws SQLException {
        userDao.delete(id);
    }

    @Override
    public User search(Long id) throws SQLException {
        return userDao.search(id);
    }

    @Override
    public List<User> searchAll() throws SQLException {
        return userDao.searchAll();
    }
}
