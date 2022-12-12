package com.integradordh.trabajofinal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Field;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dentists")
public class Dentist {

    @Id
    @SequenceGenerator(name = "dentist_sequence", sequenceName = "dentist_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dentist_sequence")
    private Long id;
    private String name;
    private String lastName;
    private String licenseNumber;


    public Dentist(String name, String lastName, String licenseNumber) {
        this.name = name;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return "\nName = " + this.getName() +
                "\nLastname = " + this.getLastName() +
                "\nLicense number = " + this.licenseNumber;
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
