package org.example;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:h2:"+ "./Database/my", "root", "myPassword");
        Statement stmt = con.createStatement();


        String createSQL = "DROP TABLE IF EXISTS FIGURAS;\n" +
                "CREATE TABLE FIGURAS(ID INT, NAME VARCHAR(25), COLOR VARCHAR(25));\n" +
                "INSERT INTO FIGURAS VALUES(1,'Circulo','rojo');" +
                "INSERT INTO FIGURAS VALUES(2,'Cuadrado','verde');" +
                "INSERT INTO FIGURAS VALUES(3,'Rectangulo','azul');" +
                "INSERT INTO FIGURAS VALUES(4,'Triangulo','amarillo');";

        stmt.execute(createSQL);

        String sql = "select * from FIGURAS";
        ResultSet rd = stmt.executeQuery(sql);

        while(rd.next()) {
            System.out.println(rd.getInt(1)+ " " + rd.getString(2) + " " + rd.getString(3));
        }



    }
}
