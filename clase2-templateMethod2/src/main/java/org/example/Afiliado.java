package org.example;

public class Afiliado extends Vendedor {

    // Construcotr para afiliados antiguos
    public Afiliado( int cantidadVentas, String nombre, String apellido) {
        super(cantidadVentas, nombre, apellido);
    }

    // Constructor para afiliados nuevos

    public Afiliado (String nombre , String apellido) {
        super(nombre, apellido);

    }

    @Override
    public void calcularPuntos() {

        setPuntosAlcanzados(getPuntosAlcanzados() + (getCantidadVentas() * 15));

    }
}
