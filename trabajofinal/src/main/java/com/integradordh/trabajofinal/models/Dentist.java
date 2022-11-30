package com.integradordh.trabajofinal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dentists_tb")
public class Dentist extends User{

    @Id
    private Long id;

    private String licenseNumber;

    @OneToMany(mappedBy = "dentist")
    @JsonIgnore
    private Set<Appointment> appointmentSet;

    public Dentist(Long id, String name, String lastName, String licenseNumber) {
        super(id, name, lastName);
        this.licenseNumber = licenseNumber;
    }

    public Dentist(String name, String lastName, String licenseNumber) {
        super(name, lastName);
        this.licenseNumber = licenseNumber;
    }
}
