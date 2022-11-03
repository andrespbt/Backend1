package test.com.dh.integrador.dao;

import main.com.dh.integrador.dao.IDao;
import main.com.dh.integrador.dao.impl.AppointmentDAOH2;
import main.com.dh.integrador.dao.impl.DentistDAOH2;
import main.com.dh.integrador.dao.impl.PatientDAOH2;
import main.com.dh.integrador.model.Appointment;
import main.com.dh.integrador.model.Dentist;
import main.com.dh.integrador.model.Patient;
import main.com.dh.integrador.service.DentistService;
import main.com.dh.integrador.service.PatientService;
import org.junit.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

@RunWith(JUnit4.class)
public class AppointmentDAOH2Test {

    private static IDao<Appointment> appointmentDAO = new AppointmentDAOH2();
    private static PatientService patientService = new PatientService(new PatientDAOH2());
    private static DentistService dentistservice = new DentistService(new DentistDAOH2());



    @BeforeClass
    public static void deletingData() throws SQLException {
        // Patients, dentist and appointment will use id = 5
        // Clear data before each test

        if (appointmentDAO.search(5L) != null) {
            appointmentDAO.delete(5L);
            patientService.delete(5L);
            dentistservice.delete(5L);

        }

    }

    @Test
    public void savingAndSearchingAppointment() throws SQLException {

        Patient patient = new Patient(5L,"Juan","Rodriguez","user","1234",new Date(122, 10,25));
        Dentist dentist = new Dentist(5L,"Pedro","Martinez","user","AC3221BM");
        patientService.save(patient);
        dentistservice.save(dentist);

        Appointment appointment = new Appointment(5L, dentist, patient,new Date(122,10,29), new Time(17, 20, 35));
        appointmentDAO.save(appointment);

        Appointment newAppointment = appointmentDAO.search(5L);

        Assert.assertNotNull(newAppointment);
        Assert.assertEquals(
                appointment.getDentist().toString(),
         "\nDentist: " +
                 "\nID: " + 5 +
                 "\nName: " +
                 "Pedro" +
                 "\nLastname: " +
                 "Martinez" +
                 "\nLicense number: " +
                 "AC3221BM"
                );

    }

    @Test
    public void showAllAppointments() throws SQLException {

        Patient patient = new Patient(5L,"Juan","Rodriguez","user","1234",new Date(122, 10,25));
        Dentist dentist = new Dentist(5L,"Pedro","Martinez","user","AC3221BM");
        patientService.save(patient);
        dentistservice.save(dentist);

        Appointment appointment = new Appointment(5L, dentist, patient,new Date(122,10,29), new Time(17, 20, 35));
        appointmentDAO.save(appointment);

        List<Appointment> newAppointments = appointmentDAO.searchAll();

        Assert.assertTrue(newAppointments.size() > 0);

    }

    @Test
    public void deleteAppointment() throws SQLException {

        Patient patient = new Patient(5L,"Juan","Rodriguez","user","1234",new Date(122, 10,25));
        Dentist dentist = new Dentist(5L,"Pedro","Martinez","user","AC3221BM");
        patientService.save(patient);
        dentistservice.save(dentist);

        Appointment appointment = new Appointment(5L, dentist, patient,new Date(122,10,29), new Time(17, 20, 35));
        appointmentDAO.save(appointment);

        appointmentDAO.delete(5L);

        Assert.assertNull(appointmentDAO.search(5L));

    }

}
