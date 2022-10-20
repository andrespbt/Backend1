package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

       boolean x = esPar(8);
        System.out.println(x);

    }

    public static boolean esPar(int n){
        return n % 2 == 0;
    }
}
