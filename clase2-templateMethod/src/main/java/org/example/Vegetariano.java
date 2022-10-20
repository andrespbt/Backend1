package org.example;

public class Vegetariano extends Menu {

    private int cantidadEspecias;
    private int cantidadSalsasVeggie;


    public Vegetariano(Double precioBase, int cantidadEspecias, int cantidadSalsasVeggie) {
        super(precioBase);
        this.cantidadEspecias = cantidadEspecias;
        this.cantidadSalsasVeggie = cantidadSalsasVeggie;
    }

    public int getCantidadEspecias() {
        return cantidadEspecias;
    }

    public void setCantidadEspecias(int cantidadEspecias) {
        this.cantidadEspecias = cantidadEspecias;
    }

    public int getCantidadSalsasVeggie() {
        return cantidadSalsasVeggie;
    }

    public void setCantidadSalsasVeggie(int cantidadSalsasVeggie) {
        this.cantidadSalsasVeggie = cantidadSalsasVeggie;
    }

    public Double calcularTotal() {
        return getPrecioBase() + ((getPrecioBase() * 0.01) * cantidadEspecias) + (2 * cantidadSalsasVeggie);
    }

    public void armarMenu() {

        System.out.println("Armando menu Veggie \nCantidad de especias: " + cantidadEspecias + "\nCantidad de salsas veggies: "+ cantidadSalsasVeggie + "\n" + "Total: " + "$" +calcularTotal());

    }
}
