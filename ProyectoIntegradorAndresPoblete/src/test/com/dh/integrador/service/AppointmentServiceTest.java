package test.com.dh.integrador.service;

import main.com.dh.integrador.dao.IDao;
import main.com.dh.integrador.dao.impl.AppointmentDAOH2;
import main.com.dh.integrador.dao.impl.DentistDAOH2;
import main.com.dh.integrador.dao.impl.PatientDAOH2;
import main.com.dh.integrador.model.Appointment;
import main.com.dh.integrador.model.Dentist;
import main.com.dh.integrador.model.Patient;
import main.com.dh.integrador.service.AppointmentService;
import main.com.dh.integrador.service.DentistService;
import main.com.dh.integrador.service.PatientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class AppointmentServiceTest {

    private IDao<Appointment> appointmentDAO = new AppointmentDAOH2();
    private AppointmentService appointmentService = new AppointmentService();

    private static PatientService patientService = new PatientService(new PatientDAOH2());

    private static DentistService dentistservice = new DentistService(new DentistDAOH2());

    @Before
    public void loadAppointmentChangingDAOImplementation() throws SQLException {

        appointmentService.setAppointmentDAO(appointmentDAO);

        if(appointmentService.search(8L) == null){
            Patient patient = new Patient(8L,"Juan","Rodriguez","user","1234",new Date(122, 10,25));
            Dentist dentist = new Dentist(8L,"Pedro","Martinez","user","AC3221BM");
            patientService.save(patient);
            dentistservice.save(dentist);

            Appointment appointment = new Appointment(8L, dentist, patient,new Date(122,10,29), new Time(17, 20, 35));
            appointmentDAO.save(appointment);
        }

    }

    @Test
    public void searchStudentUsingImplementation() throws SQLException {

        Appointment appointment = appointmentService.search(8L);

        Assert.assertEquals(appointment.getDentist().getName(),"Pedro");
        Assert.assertEquals(appointment.getPatient().getLastName(),"Rodriguez");


    }
}
