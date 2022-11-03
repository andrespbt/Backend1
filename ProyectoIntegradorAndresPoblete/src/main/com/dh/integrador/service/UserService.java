package main.com.dh.integrador.service;

import main.com.dh.integrador.dao.IDao;
import main.com.dh.integrador.model.Dentist;
import main.com.dh.integrador.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private IDao<User> userDao;

    public UserService() {
    }

    public UserService(IDao<User> userDao) {
        this.userDao = userDao;
    }

    public void setUserDao(IDao<User> userDao) {
        this.userDao = userDao;
    }

    public User save(User user) throws SQLException {
        userDao.save(user);
        return user;
    }

    public void delete(Long id) throws SQLException {
        userDao.delete(id);
    }

    public User search(Long id) throws SQLException {
        return userDao.search(id);
    }

    public List<User> searchAll() throws SQLException {
        return userDao.searchAll();
    }
}
