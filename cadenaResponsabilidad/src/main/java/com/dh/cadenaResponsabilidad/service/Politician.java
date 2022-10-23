package com.dh.cadenaResponsabilidad.service;

public abstract class Politician {

    protected Politician nextPolitician;

    public abstract void readMessage(Document document);

    public Politician getNextPolitician() {

        return nextPolitician;

    }

    public Politician setNextPolitician(Politician politician) {
        this.nextPolitician = politician;

        return this;
    }


}
