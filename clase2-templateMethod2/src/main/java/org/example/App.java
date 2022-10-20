package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Empleado empleado1 = new Empleado(0,"Diana","Cruz",0,0);

        System.out.println(empleado1.mostrarCategoria());
        empleado1.vender();
        empleado1.vender();
        empleado1.vender();
        empleado1.vender();
        empleado1.setAñosAntiguedad(2);
        empleado1.conseguirAfiliado();
        empleado1.conseguirAfiliado();
        System.out.println(empleado1.mostrarCategoria());

        Afiliado afiliado1 = new Afiliado("Martin", "Garritano");
        System.out.println(afiliado1.mostrarCategoria());
        afiliado1.vender();
        afiliado1.vender();
        System.out.println(afiliado1.mostrarCategoria());


        Afiliado afiliado2 = new Afiliado(5,"Pablo","Perez");
        System.out.println(afiliado2.mostrarCategoria());


    }
}
