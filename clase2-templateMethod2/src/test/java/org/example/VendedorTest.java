package org.example;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class VendedorTest {

    @Test
    void mostrarCategoria() {

        // Empleado tests

        Empleado empleado1 = new Empleado(0, "Andres","Poblete",0,0);
        String message = "Andres Poblete tiene un total de 0 puntos, categoriza como novato/a";
        String message1 = "Andres Poblete tiene un total de 20 puntos, categoriza como aprendiz";


        assertEquals(message, empleado1.mostrarCategoria());

        empleado1.conseguirAfiliado();
        empleado1.vender();
        empleado1.vender();

        assertEquals(message1, empleado1.mostrarCategoria());

        empleado1.conseguirAfiliado();
        empleado1.vender();

        empleado1.mostrarCategoria();

        assertEquals("bueno/a", empleado1.getCategoria());


        // Afiliado tests

        Afiliado afiliado1 = new Afiliado("Roman","Riquelme");
        String message2 = "Roman Riquelme tiene un total de 0 puntos, categoriza como novato/a";

        assertEquals(message2, afiliado1.mostrarCategoria());

        Afiliado afiliado2 = new Afiliado(10, "Lucas","Martinez");
        String message3 = "Lucas Martinez tiene un total de 150 puntos, categoriza como maestro/a";

        assertEquals(message3, afiliado2.mostrarCategoria());


    }
}