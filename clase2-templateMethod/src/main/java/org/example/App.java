package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Infantil infantil1 = new Infantil(10.,4);
        infantil1.armarMenu();

        Vegetariano veggie1 = new Vegetariano(10., 10, 2);
        veggie1.armarMenu();

        Clasico clasico1 = new Clasico(10.);
        clasico1.armarMenu();

    }
}
