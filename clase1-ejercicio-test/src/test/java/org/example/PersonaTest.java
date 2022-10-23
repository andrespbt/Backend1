package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonaTest {
    Persona persona1 = new Persona("Andres","Poblete",28);

    @Test
    void showName() {
        assertEquals(persona1.showName(),"Poblete, Andres");
    }

}