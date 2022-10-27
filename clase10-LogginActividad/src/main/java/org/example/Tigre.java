package org.example;

import org.apache.log4j.Logger;

public class Tigre {

    private static final Logger logger = Logger.getLogger(Tigre.class);

    private String name;
    private int edad;

    public Tigre(String name, int edad) {
        this.name = name;
        this.edad = edad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void run () {

        logger.info("El Tigre está corriendo");

    }
}
