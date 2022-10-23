package com.dh.cadenaResponsabilidad.service.impl;

import com.dh.cadenaResponsabilidad.service.Document;
import com.dh.cadenaResponsabilidad.service.Politician;

public class Deputy extends Politician {

    public void readMessage(Document document) {
        if (document.getType() == 1 ){
            System.out.println("Read by deputy." + document.toString());
        }else {
            this.nextPolitician.readMessage(document);
        }
    }
}
