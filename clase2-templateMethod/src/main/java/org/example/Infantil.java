package org.example;

public class Infantil extends Menu {

    private int cantidadJuguetes;


    public Infantil(Double precioBase, int cantidadJuguetes) {
        super(precioBase);
        this.cantidadJuguetes = cantidadJuguetes;
    }

    public int getCantidadJuguetes() {
        return cantidadJuguetes;
    }

    public void setCantidadJuguetes(int cantidadJuguetes) {
        this.cantidadJuguetes = cantidadJuguetes;
    }

    public Double calcularTotal() {
       return getPrecioBase() + (3. * this.cantidadJuguetes);
    }

    public void armarMenu() {

        System.out.println("Armando menu Infantil \nCantidad de juguetes: " + cantidadJuguetes + "\nTotal: " + "$" +calcularTotal());

    }
}
