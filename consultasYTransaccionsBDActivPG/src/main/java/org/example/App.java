package org.example;

import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String SQL_CREATE_TABLE = "DROP TABLE IF EXISTS PACIENTES; CREATE TABLE PACIENTES"
            + "("
            + " ID INT PRIMARY KEY,"
            + " NOMBRE VARCHAR(100) NOT NULL,"
            + " APELLIDO VARCHAR(100) NOT NULL,"
            + " DOMICILIO VARCHAR(100),"
            + " DNI VARCHAR(10),"
            + " FECHAALTA VARCHAR(20),"
            + " USUARIO VARCHAR(20),"
            + " PASSWORD VARCHAR(20)"
            + ");";

    private static final String SQL_INSERT = "INSERT INTO PACIENTES (ID,NOMBRE,APELLIDO,DOMICILIO,DNI,FECHAALTA,USUARIO,PASSWORD) VALUES (?,?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE = "UPDATE PACIENTES SET PASSWORD=? WHERE USUARIO=?";
    private static final Logger logger = Logger.getLogger(App.class);

    public static void main( String[] args ) throws Exception {


        Paciente paciente = new Paciente("Andres", "Poblete", "San rafael","38290412","20/05/2022","andrecio","123");

        Connection con = null;

        try {

            con = getConnection();
            Statement stmt = con.createStatement();
            logger.info("Creando tabla");
            stmt.execute(SQL_CREATE_TABLE);
            logger.info("Tabla creada satisfactoriamente");

            PreparedStatement psInsert = con.prepareStatement(SQL_INSERT);


            // Empezar a insertar en la base de datos
            psInsert.setInt(1 , 1);
            psInsert.setString(2, paciente.getNombre());
            psInsert.setString(3, paciente.getApellido());
            psInsert.setString(4, paciente.getDomicilio());
            psInsert.setString(5, paciente.getDni());
            psInsert.setString(6, paciente.getFechaAlta());
            psInsert.setString(7, paciente.getUsuario());
            psInsert.setString(8, paciente.getPassword());
            logger.info("Usuario insertado correctamente");

            psInsert.execute();

            // Empezar la transaccion
            con.setAutoCommit(false);

            PreparedStatement psUpdate = con.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1, "456");
            psUpdate.setString(2, paciente.getUsuario());
            logger.info("Contraseña actualizada correctamente");

            psUpdate.executeUpdate();

            con.commit();

            con.setAutoCommit(true);

            String sql = "select * from PACIENTES";
            Statement stmt1 = con.createStatement();
            ResultSet rd = stmt1.executeQuery(sql);

            while(rd.next()){
                System.out.println(
                rd.getInt(1) +
                "\nNombre: " + rd.getString(2) +
                "\nApellido: " + rd.getString(3) +
                "\nDomicilio: " + rd.getString(4) +
                "\nDNI: " + rd.getString(5) +
                "\nFecha Alta: " + rd.getString(6) +
                "\nUsuario: " + rd.getString(7) +
                "\nPassword: " + rd.getString(8)
                );
            }






        }catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            logger.error("Error: ", e);

        }finally {
            con.close();
        }

    }

    private static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/myDatabase", "root", "myPassword");
    }
}
