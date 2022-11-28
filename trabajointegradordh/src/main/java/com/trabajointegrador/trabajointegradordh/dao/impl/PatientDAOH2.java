package com.trabajointegrador.trabajointegradordh.dao.impl;

import com.trabajointegrador.trabajointegradordh.dao.IDao;
import com.trabajointegrador.trabajointegradordh.model.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class PatientDAOH2 implements IDao<Patient> {

    private static final Logger logger = LogManager.getLogger(PatientDAOH2.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/parcialIntegrador;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";

    public PatientDAOH2() {
    }

    @Override
    public Patient save(Patient patient) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

       try {

           Class.forName(DB_JDBC_DRIVER);
           connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


           preparedStatement = connection.prepareStatement("INSERT INTO patient VALUES(?,?,?,?,?,?)");
           preparedStatement.setLong(1,patient.getId());
           preparedStatement.setString(2, patient.getName());
           preparedStatement.setString(3, patient.getLastName());
           preparedStatement.setString(4, patient.getRole());
           preparedStatement.setString(5, patient.getNationalId());
           preparedStatement.setDate(6, patient.getRegistrationDate());


           preparedStatement.executeUpdate();

       } catch (SQLException | ClassNotFoundException e) {
           logger.error("Error", e);
       }finally {
           preparedStatement.close();
       }

        return patient;
    }

    @Override
    public void delete(Long id) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("DELETE FROM patient WHERE id = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error", e);
        }finally {
            connection.close();
        }

    }

    @Override
    public Patient search(Long id) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Patient patient = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("SELECT id,name,lastname,role,nationalId,registrationDate FROM patient WHERE id = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeQuery();

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idPatient = result.getLong("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                String role = result.getString("role");
                String nationalId = result.getString("nationalId");
                Date registrationDate = result.getDate("registrationDate");
                patient = new Patient(idPatient, name, lastName, role, nationalId, registrationDate );
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error", e);
        }finally {
            connection.close();
        }

        return patient;
    }

    @Override
    public List<Patient> searchAll() throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List <Patient> patients = new ArrayList<>();

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("SELECT *  FROM patient");

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idPatient = result.getLong("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                String role = result.getString("role");
                String nationalId = result.getString("nationalId");
                Date registrationDate = result.getDate("registrationDate");
                patients.add(new Patient(idPatient,name,lastName,role,nationalId,registrationDate));
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error", e);
        }finally {
            connection.close();
        }

        return patients;
    }
}
