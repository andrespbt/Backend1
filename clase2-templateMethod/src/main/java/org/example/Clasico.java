package org.example;

public class Clasico extends Menu {
    public Clasico(Double precioBase) {
        super(precioBase);
    }

    public Double calcularTotal() {
        return getPrecioBase();
    }

    public void armarMenu() {

        System.out.println("Armando menu Clasico\nTotal: " + "$" + calcularTotal());

    }
}
