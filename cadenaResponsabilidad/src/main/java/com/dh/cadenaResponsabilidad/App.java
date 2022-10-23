package com.dh.cadenaResponsabilidad;

import com.dh.cadenaResponsabilidad.service.Document;
import com.dh.cadenaResponsabilidad.service.Politician;
import com.dh.cadenaResponsabilidad.service.impl.Deputy;
import com.dh.cadenaResponsabilidad.service.impl.Minister;
import com.dh.cadenaResponsabilidad.service.impl.President;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Document message = new Document("Hello World", 3);
        Politician baseHandler = new Deputy().setNextPolitician(new Minister().setNextPolitician(new President()));

        baseHandler.readMessage(message);

    }
}
