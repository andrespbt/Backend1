package test.com.dh.integrador.service;

import main.com.dh.integrador.dao.IDao;
import main.com.dh.integrador.dao.impl.DentistDAOH2;
import main.com.dh.integrador.model.Dentist;
import main.com.dh.integrador.service.AppointmentService;
import main.com.dh.integrador.service.DentistService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class DentistServiceTest {

    private IDao<Dentist> dentistDAO = new DentistDAOH2();
    private DentistService dentistService = new DentistService();

    @Before
    public void loadDentistServiceChangingDaoImplementation() throws SQLException {
        dentistService.setDentistDao(dentistDAO);

        if (dentistService.search(9L) == null) {
            dentistService.save(new Dentist(9L,"Mariano","Marcos","user","CAM2131MSA"));

        }
    }

    @Test
    public void searchDentistUsingImplementationDAO() throws SQLException {
        Dentist dentist1 = dentistService.search(9L);
        Assert.assertEquals(dentist1.getName(), "Mariano");

    }
}
