package org.example;

public class Empleado extends Vendedor {
    private int cantidadAfiliados = 0;

    private int añosAntiguedad = 0;


    // Constructor para empleados antiguos con años de antiguedad, afiliados y cantidad de ventas
    public Empleado(int cantidadVentas, String nombre, String apellido, int cantidadAfiliados, int añosAntiguedad) {
        super(cantidadVentas, nombre, apellido);
        this.cantidadAfiliados = cantidadAfiliados;
        this.añosAntiguedad = añosAntiguedad;
    }

    // Constructor para empleados nuevos, sin años de antiguedad, afiliados ni ventas

    public Empleado(String nombre, String apellido) {
        super(nombre, apellido);

    }



    public int getCantidadAfiliados() {
        return cantidadAfiliados;
    }

    public void setCantidadAfiliados(int cantidadAfiliados) {
        this.cantidadAfiliados = cantidadAfiliados;
    }

    public int getAñosAntiguedad() {
        return añosAntiguedad;
    }

    public void setAñosAntiguedad(int añosAntiguedad) {
        this.añosAntiguedad = añosAntiguedad;
    }

    public void calcularPuntos() {

        setPuntosAlcanzados((getCantidadAfiliados() * 10) + (getCantidadVentas() * 5) + (getAñosAntiguedad() * 5) );

    }

    public void conseguirAfiliado(){

        setCantidadAfiliados(getCantidadAfiliados() + 1);

    }
}
