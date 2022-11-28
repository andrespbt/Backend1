package com.trabajointegrador.trabajointegradordh.dao.impl;

import com.trabajointegrador.trabajointegradordh.dao.IDao;
import com.trabajointegrador.trabajointegradordh.model.Dentist;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

@Component
public class DentistDAOH2 implements IDao<Dentist> {

    private static final Logger logger = LogManager.getLogger(DentistDAOH2.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/parcialIntegrador;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";

    public DentistDAOH2() {
    }


    @Override
    public Dentist save(Dentist dentist) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("INSERT INTO dentist VALUES(?,?,?,?,?)");
            preparedStatement.setLong(1,dentist.getId());
            preparedStatement.setString(2, dentist.getName());
            preparedStatement.setString(3, dentist.getLastName());
            preparedStatement.setString(4, dentist.getRole());
            preparedStatement.setString(5, dentist.getLicenseNumber());


            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error", e);
        }

        return dentist;
    }

    @Override
    public void delete(Long id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("DELETE FROM dentist WHERE id = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error", e);
        }

    }

    @Override
    public Dentist search(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Dentist dentist = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("SELECT id,name,lastname,role,licenseNumber FROM dentist WHERE id = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeQuery();

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idDentist = result.getLong("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                String role = result.getString("role");
                String licenseNumber = result.getString("licenseNumber");
                dentist = new Dentist(idDentist, name, lastName, role, licenseNumber );
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error", e);
        }

        return dentist;
    }

    @Override
    public List<Dentist> searchAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List <Dentist> dentists = new ArrayList<>();

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("SELECT *  FROM dentist");

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idDentist = result.getLong("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                String role = result.getString("role");
                String licenseNumber = result.getString("licenseNumber");
                dentists.add(new Dentist(idDentist,name,lastName,role,licenseNumber));
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error", e);
        }

        return dentists;
    }
}
