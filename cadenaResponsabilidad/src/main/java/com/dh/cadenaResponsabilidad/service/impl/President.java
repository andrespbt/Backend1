package com.dh.cadenaResponsabilidad.service.impl;

import com.dh.cadenaResponsabilidad.service.Document;
import com.dh.cadenaResponsabilidad.service.Politician;

public class President extends Politician {
    public void readMessage(Document document) {
        if (document.getType() <= 3 ){
            System.out.println("Read by president." + document.toString());
        }else {
            this.nextPolitician.readMessage(document);
        }
    }
}
