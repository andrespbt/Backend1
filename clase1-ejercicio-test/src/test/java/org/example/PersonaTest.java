package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonaTest {
    Persona persona1 = new Persona("Andres","Poblete",28);

    @Test
    void showName() {
        assertEquals(persona1.showName(),"Poblete, Andres");
    }

}