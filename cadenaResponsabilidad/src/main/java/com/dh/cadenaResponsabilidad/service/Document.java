package com.dh.cadenaResponsabilidad.service;

public class Document {

    private String content;
    private int type;

    public Document(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String typeDocument = null;

        switch (type) {
            case 1:
                typeDocument = "Reserved";
                break;
            case 2:
                typeDocument = "Secret";
                break;
            case 3:
                typeDocument = "Top secret";
                break;

        }

        return "\nCategory: " + typeDocument + "\nMessage: " + content ;
    }
}
