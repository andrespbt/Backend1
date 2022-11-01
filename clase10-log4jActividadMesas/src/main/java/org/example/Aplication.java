package org.example;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Aplication {

    private static final Logger logger = Logger.getLogger(Aplication.class);

    private List<Integer>ListaEnteros = new ArrayList<Integer>();

    public Aplication(List<Integer> listaEnteros) {
        ListaEnteros = listaEnteros;
    }

    public List<Integer> getListaEnteros() {
        return ListaEnteros;
    }

    public void setListaEnteros(List<Integer> listaEnteros) {
        ListaEnteros = listaEnteros;
    }


    public void numeroElmentos() throws Exception {
        if (this.ListaEnteros.size() >= 5 && this.ListaEnteros.size() <= 10) {
            logger.info("La lista tiene mas de 5 elementos");
        } else if (this.ListaEnteros.size() > 10) {
            logger.info("La lista tiene mas de 10 elementos");
        }else if (this.ListaEnteros.size() == 0){
            logger.error("La lista es igual a cero ");
        }


        int promedio = 0;

        try {
            for( int entero : this.ListaEnteros){

                promedio += entero;

            }

            promedio = promedio / this.ListaEnteros.size();

            logger.info("Promedio: " + promedio);

        }catch (Exception e) {
            logger.error("No hay elementos para iterar");
            e.printStackTrace();

        }


        }


    }
