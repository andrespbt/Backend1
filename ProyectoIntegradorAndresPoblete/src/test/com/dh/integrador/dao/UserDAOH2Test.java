package test.com.dh.integrador.dao;

import main.com.dh.integrador.dao.IDao;
import main.com.dh.integrador.dao.impl.UserDAOH2;
import main.com.dh.integrador.model.Patient;
import main.com.dh.integrador.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class UserDAOH2Test {

    private static IDao<User> userDao = new UserDAOH2();

    @BeforeClass
    public static void loadPatients() throws SQLException {

        if (userDao.search(3L) == null) {
            userDao.save(new User(3L,"Lucas","Marquez","user"));
            userDao.save(new User(4L,"Pedro","Ramos","user"));
            userDao.save(new User(5L,"Homero","Simpson","user"));
            userDao.save(new User(6L,"Ryoshi","Nakamoto","user"));

        }

    }

    @Test
    public void saveAndSearchPatient() throws SQLException {
        userDao.save(new User(7L,"Pepito","Cruz","user"));
        User user = userDao.search(7L);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getName(),"Pepito");

    }

    @Test
    public void searchAllPatients() throws SQLException {
        List<User> userList = userDao.searchAll();
        Assert.assertTrue(userList.size() > 0);
    }


    @Test
    public void deleteDentist() throws SQLException {
        userDao.delete(3L);
        Assert.assertNull(userDao.search(3L));
        userDao.delete(4L);
        Assert.assertNull(userDao.search(4L));
        userDao.delete(5L);
        Assert.assertNull(userDao.search(5L));
        userDao.delete(6L);
        Assert.assertNull(userDao.search(6L));
        userDao.delete(7L);
        Assert.assertNull(userDao.search(7L));
    }
}
