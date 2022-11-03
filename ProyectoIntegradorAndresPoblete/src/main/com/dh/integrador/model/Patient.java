package main.com.dh.integrador.model;

import java.time.LocalDate;
import java.sql.Date;

public class Patient extends User {

    private Long id;
    private String nationalId;
    private Date registrationDate;

    public Patient() {
    }

    public Patient(Long id, String name, String lastName, String role, String nationalId, Date registrationDate) {
        super(name, lastName, role);
        this.id = id;
        this.nationalId = nationalId;
        this.registrationDate = registrationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "\nPatient: " + "\nID: " + id + "\nName: " + getName() + "\nLastname: " + getLastName() + "\nNational id: " + nationalId + "\nRegistration date: " + registrationDate;
    }
}
