package test.com.dh.integrador.dao;

import main.com.dh.integrador.dao.IDao;
import main.com.dh.integrador.dao.impl.DentistDAOH2;
import main.com.dh.integrador.dao.impl.PatientDAOH2;
import main.com.dh.integrador.model.Dentist;
import main.com.dh.integrador.model.Patient;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class PatientDAOH2Test {

    private  static IDao<Patient> patientDao = new PatientDAOH2();

    @BeforeClass
    public static void loadPatients() throws SQLException {

        if (patientDao.search(3L) == null) {
            patientDao.save(new Patient(3L,"Lucas","Marquez","user","412414",new Date(121,5,15)));
            patientDao.save(new Patient(4L,"Pedro","Ramos","user","412312", new Date(120,6,19)));
            patientDao.save(new Patient(5L,"Homero","Simpson","user","1231314",new Date(122,3,11)));
            patientDao.save(new Patient(6L,"Ryoshi","Nakamoto","user","23817387",new Date(122,2,25)));

        }

    }

    @Test
    public void saveAndSearchPatient() throws SQLException {
        patientDao.save(new Patient(7L,"Pepito","Cruz","user","1231341",new Date(122,3,23)));
        Patient patient = patientDao.search(7L);
        Assert.assertNotNull(patient);
        Assert.assertEquals(patient.getName(),"Pepito");

    }

    @Test
    public void searchAllPatients() throws SQLException {
        List<Patient> patientsList = patientDao.searchAll();
        Assert.assertTrue(patientsList.size() > 0);
    }


    @Test
    public void deleteDentist() throws SQLException {
        patientDao.delete(3L);
        Assert.assertNull(patientDao.search(3L));
        patientDao.delete(4L);
        Assert.assertNull(patientDao.search(4L));
        patientDao.delete(5L);
        Assert.assertNull(patientDao.search(5L));
        patientDao.delete(6L);
        Assert.assertNull(patientDao.search(6L));
        patientDao.delete(7L);
        Assert.assertNull(patientDao.search(7L));
    }
}
