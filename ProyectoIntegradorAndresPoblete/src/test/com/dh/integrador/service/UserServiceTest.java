package test.com.dh.integrador.service;

import main.com.dh.integrador.dao.IDao;
import main.com.dh.integrador.dao.impl.UserDAOH2;
import main.com.dh.integrador.model.User;
import main.com.dh.integrador.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

public class UserServiceTest {

    private IDao<User> userDao = new UserDAOH2();
    private UserService userService = new UserService();

    @Before
    public void loadDentistServiceChangingDaoImplementation() throws SQLException {
        userService.setUserDao(userDao);

        if (userService.search(9L) == null) {
            userService.save(new User(9L,"Mariano","Marcos","user"));

        }
    }

    @Test
    public void searchDentistUsingImplementationDAO() throws SQLException {
        User user1 = userService.search(9L);
        Assert.assertEquals(user1.getName(), "Mariano");

    }
}
