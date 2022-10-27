package org.example;

import org.apache.log4j.Logger;

public class Leon {
    private static final Logger logger = Logger.getLogger(Leon.class);

    private String name;
    private int age;
    private boolean isAlpha;

    public Leon(String name, int age, boolean isAlpha) {
        this.name = name;
        this.age = age;
        this.isAlpha = isAlpha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAlpha() {
        return isAlpha;
    }

    public void setAlpha(boolean alpha) {
        isAlpha = alpha;
    }

    public void run () {

        logger.info("El leon " + name + " está corriendo");

    }

    public void isAdultAndAlpha() throws Exception {

        if (this.age < 0){
            Exception e;
            logger.error("La edad del leon es menor a 0");

        }else {
            if( this.isAlpha && this.age > 10) {
                logger.info("El leon es mayor y es alpha");
            }else {
                logger.info("El leon no es mayor o no es alpha");

            }

        }

    }
}
