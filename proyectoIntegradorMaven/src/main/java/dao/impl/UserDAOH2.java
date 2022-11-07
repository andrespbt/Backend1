package dao.impl;

import dao.IDao;
import model.User;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOH2 implements IDao<User> {

    private static final Logger logger = Logger.getLogger(UserDAOH2.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/parcialIntegrador;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";


    public UserDAOH2() {
    }



    @Override
    public User save(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("INSERT INTO users VALUES(?,?,?,?)");
            preparedStatement.setLong(1,user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getRole());

            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
           logger.error("Error", e);
        }finally {
            preparedStatement.close();
        }

        return user;
    }

    @Override
    public void delete(Long id) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
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
    public User search(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        User user = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("SELECT id,name,lastname,role FROM users WHERE id = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeQuery();

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idUser = result.getLong("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                String role = result.getString("role");
                user = new User(idUser, name, lastName, role);
            }

            preparedStatement.close();


        } catch (SQLException | ClassNotFoundException e) {
           logger.error("Error", e);
        }

        return user;
    }

    @Override
    public List<User> searchAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        User user = null;
        List<User> users = new ArrayList<>();

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("SELECT * FROM users");

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idUser = result.getLong("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                String role = result.getString("role");
                user = new User(idUser, name, lastName, role);
                users.add(user);
            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error", e);
        }finally {
            connection.close();
        }

        return users;
    }
}
