package org.example;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws Exception
    {

        Class.forName("org.h2.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:h2:"+
                "./Database/my", "root", "myPassword");
        Statement stmt = con.createStatement();


        String createTable = "DROP TABLE IF EXISTS EMPLOYEE;\n" +
                "CREATE TABLE EMPLOYEE(ID INT, NAME VARCHAR(50), AGE INT, COMPANY VARCHAR(50), DATE DATE);\n" +
                "INSERT INTO EMPLOYEE VALUES(1,'Andres',28,'Digital','2015-06-10');\n" +
                "INSERT INTO EMPLOYEE VALUES(2,'Juan',29,'Google','2020-02-10');\n" +
                "INSERT INTO EMPLOYEE VALUES(3,'Pedro',35,'Facebook','2010-08-02');\n";

        stmt.execute(createTable);

        //Codigo para consultar todos los registros de la tabla TEST
        String sql = "select * from EMPLOYEE";
        ResultSet rd = stmt.executeQuery(sql);


        //Código para recorrer el resultado de la consulta
        while(rd.next()) {
            System.out.println(rd.getInt(1)+ " " + rd.getString(2) + " " + rd.getInt(3) + " " + rd.getString(4) + " "+rd.getString(5) + " ");
        }

    }
}
