package com.integradordh.trabajofinal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patients_tb")
public class Patient extends User {


    private Long id;
    private String nationalId;
    private String registrationDate;
    private String address;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private Set<Appointment> appointmentSet;

    public Patient(Long id, String name, String lastName, String nationalId, Date registrationDate, String address) {
        super(id, name, lastName);
        this.nationalId = nationalId;
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        this.registrationDate = DateFor.format(registrationDate);
        this.address = address;
    }

    public Patient(String nationalId, Date registrationDate, String address) {
        this.nationalId = nationalId;
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        this.registrationDate = DateFor.format(registrationDate);
        this.address = address;
    }

    public Patient(String name, String lastName, String nationalId, Date registrationDate, String address) {
        super(name, lastName);
        this.nationalId = nationalId;
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        this.registrationDate = DateFor.format(registrationDate);
        this.address = address;
    }
}
