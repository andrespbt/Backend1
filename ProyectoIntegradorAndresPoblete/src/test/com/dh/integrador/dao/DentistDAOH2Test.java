package test.com.dh.integrador.dao;

import main.com.dh.integrador.dao.IDao;
import main.com.dh.integrador.dao.impl.DentistDAOH2;
import main.com.dh.integrador.model.Dentist;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class DentistDAOH2Test {

    private  static IDao<Dentist> dentistDao = new DentistDAOH2();

    @BeforeClass
    public static void loadDentists() throws SQLException {

        if (dentistDao.search(3L) == null) {
            dentistDao.save(new Dentist(3L,"Lucas","Marquez","user","AVEC123MA"));
            dentistDao.save(new Dentist(4L,"Pedro","Ramos","user","MZEC130MV"));
            dentistDao.save(new Dentist(5L,"Homero","Simpson","user","LPCAW666MNA"));
            dentistDao.save(new Dentist(6L,"Ryoshi","Nakamoto","user","SHIB016CAW"));

        }

    }

    @Test
    public void saveAndSearchDentist() throws SQLException {
        dentistDao.save(new Dentist(7L,"Pepito","Cruz","user","LPKM112MA"));
        Dentist dentist = dentistDao.search(7L);
        Assert.assertNotNull(dentist);
        Assert.assertEquals(dentist.getName(),"Pepito");

    }

    @Test
    public void searchAllDentists() throws SQLException {
        List<Dentist> dentistList = dentistDao.searchAll();
        Assert.assertTrue(dentistList.size() > 0);
    }


    @Test
    public void deleteDentist() throws SQLException {
        dentistDao.delete(3L);
        Assert.assertNull(dentistDao.search(3L));
        dentistDao.delete(4L);
        Assert.assertNull(dentistDao.search(4L));
        dentistDao.delete(5L);
        Assert.assertNull(dentistDao.search(5L));
        dentistDao.delete(6L);
        Assert.assertNull(dentistDao.search(6L));
        dentistDao.delete(7L);
        Assert.assertNull(dentistDao.search(7L));
    }
}
