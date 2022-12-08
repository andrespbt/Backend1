package com.integradordh.trabajofinal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PATIENTS")
public class Patient {


    @Id
    @SequenceGenerator(name = "patient_sequence", sequenceName = "patient_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_sequence")
    private Long id;
    private String name;
    private String lastName;
    private String nationalId;
    private LocalDate registrationDate;
    private String address;


    public Patient(String name, String lastName, String nationalId, LocalDate registrationDate, String address) {
        this.name = name;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.registrationDate = registrationDate;
        this.address = address;
    }

    @Override
    public String toString() {
        return "\nName: " + this.name +
                "\nLast name: " + this.lastName +
                "\nNational id: " + this.nationalId +
                "\nRegistration date: " + this.registrationDate +
                "\nAddress: " + this.address;
    }

    // Method used to update
    public void merge(Object newObject) {

        assert this.getClass().getName().equals(newObject.getClass().getName());

        for (Field field : this.getClass().getDeclaredFields()) {

            for (Field newField : newObject.getClass().getDeclaredFields()) {

                if (field.getName().equals(newField.getName())) {

                    try {

                        field.set(
                                this,
                                newField.get(newObject) == null
                                        ? field.get(this)
                                        : newField.get(newObject));

                    } catch (IllegalAccessException ignore) {
                        // Field update exception on final modifier and other cases.
                    }
                }
            }
        }
    }
}
