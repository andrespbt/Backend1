package dao.impl;


import dao.IDao;
import model.Appointment;
import org.apache.log4j.Logger;
import service.DentistService;
import service.PatientService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOH2 implements IDao<Appointment> {

    private static final Logger logger = Logger.getLogger(AppointmentDAOH2.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/parcialIntegrador;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";

    PatientService patientService = new PatientService(new PatientDAOH2());
    DentistService dentistservice = new DentistService(new DentistDAOH2());

    public AppointmentDAOH2() {
    }


    @Override
    public Appointment save(Appointment appointment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("INSERT INTO appointment VALUES(?,?,?,?,?)");
            preparedStatement.setLong(1,appointment.getId());
            preparedStatement.setLong(2, appointment.getDentist().getId());
            preparedStatement.setLong(3, appointment.getPatient().getId());
            preparedStatement.setDate(4, appointment.getDate());
            preparedStatement.setTime(5, appointment.getTime());


            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error", e);
        }

        return appointment;
    }

    @Override
    public void delete(Long id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("DELETE FROM appointment WHERE id = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error", e);
        }

    }

    @Override
    public Appointment search(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Appointment appointment = null;


        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("SELECT id,dentistID_FK,patientID_FK,dateA,timeA FROM appointment WHERE id = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeQuery();

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idAppointment = result.getLong("id");
                Long dentistId = result.getLong("dentistID_FK");
                Long patientId = result.getLong("patientID_FK");
                Date date = result.getDate("dateA");
                Time time = result.getTime("timeA");
                appointment = new Appointment(idAppointment, dentistservice.search(dentistId), patientService.search(patientId), date, time );
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error", e);
        }

        return appointment;
    }

    @Override
    public List<Appointment> searchAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("SELECT *  FROM appointment");

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idAppointment = result.getLong("id");
                Long dentistId = result.getLong("dentistID_FK");
                Long patientId = result.getLong("patientID_FK");
                Date date = result.getDate("dateA");
                Time time = result.getTime("timeA");
                appointment = new Appointment(idAppointment, dentistservice.search(dentistId), patientService.search(patientId), date, time );
                appointments.add(appointment);
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error", e);
        }

        return appointments;
    }
}
