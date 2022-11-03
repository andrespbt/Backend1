package test.com.dh.integrador.service;

import main.com.dh.integrador.dao.IDao;

import main.com.dh.integrador.dao.impl.PatientDAOH2;
import main.com.dh.integrador.model.Patient;
import main.com.dh.integrador.service.PatientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

public class PatientServiceTest {

    private IDao<Patient> patientDAO = new PatientDAOH2();
    private PatientService patientService = new PatientService();

    @Before
    public void loadDentistServiceChangingDaoImplementation() throws SQLException {
        patientService.setPatientDao(patientDAO);

        if (patientService.search(9L) == null) {
            patientService.save(new Patient(9L,"Mariano","Marcos","user","23817238",new Date(122,5,5)));

        }
    }

    @Test
    public void searchDentistUsingImplementationDAO() throws SQLException {
        Patient patient1 = patientService.search(9L);
        Assert.assertEquals(patient1.getName(), "Mariano");

    }
}
