package org.example;

public abstract class Vendedor {

    private int puntosAlcanzados = 0;
    private String categoria;
    private int cantidadVentas = 0;
    private String nombre;
    private String apellido;


    // Constructor para empleados antiguos con ventas ya realizadas

    public Vendedor( int cantidadVentas, String nombre, String apellido) {
        this.cantidadVentas = cantidadVentas;
        this.nombre = nombre;
        this.apellido = apellido;

    }

    // Constructor para empleados nuevos, default 0 ventas y 0 puntos

    public Vendedor(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getPuntosAlcanzados() {
        return puntosAlcanzados;
    }

    public void setPuntosAlcanzados(int puntosAlcanzados) {
        this.puntosAlcanzados = puntosAlcanzados;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String mostrarCategoria(){

        calcularPuntos();

        recategorizar();

        return getNombre() + " " + getApellido() + " tiene un total de " + getPuntosAlcanzados() + " puntos, categoriza como " + getCategoria();
    };
    public void vender() {
        setCantidadVentas(getCantidadVentas() + 1);
    };
    public abstract void calcularPuntos();
    public void recategorizar() {

        if ( getPuntosAlcanzados() < 20){
            setCategoria("novato/a");
        }else if ( getPuntosAlcanzados() >= 20 && getPuntosAlcanzados() <= 30){
            setCategoria("aprendiz");
        }else if ( getPuntosAlcanzados() >= 31 && getPuntosAlcanzados() <= 40) {
            setCategoria("bueno/a");
        }else {
            setCategoria("maestro/a");
        }

    };

}
