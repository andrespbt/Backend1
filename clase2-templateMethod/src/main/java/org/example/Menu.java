package org.example;

public abstract class Menu {
    private Double precioBase;

    public Menu(Double precioBase) {
        this.precioBase = precioBase;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

    public abstract Double calcularTotal();
    public abstract void armarMenu();
}
