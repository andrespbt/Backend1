package com.dh.cadenaResponsabilidad.service.impl;

import com.dh.cadenaResponsabilidad.service.Document;
import com.dh.cadenaResponsabilidad.service.Politician;

public class Minister extends Politician {
    public void readMessage(Document document) {
        if (document.getType() <= 2 ){
            System.out.println("Read by minister." + document.toString());
        }else {
            this.nextPolitician.readMessage(document);
        }
    }
}
