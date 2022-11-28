package com.trabajointegrador.trabajointegradordh.model;

public class Dentist extends User{

    private Long id;

    private String licenseNumber;

    public Dentist() {
    }

    public Dentist(Long id, String name, String lastName, String role, String licenseNumber) {
        super(name, lastName, role);
        this.licenseNumber = licenseNumber;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return "\nDentist: " + "\nID: " + getId() + "\nName: " + getName() + "\nLastname: " + getLastName() + "\nLicense number: " + getLicenseNumber();
    }
}
