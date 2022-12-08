package com.integradordh.trabajofinal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "dentist_id", nullable = false)
    private Dentist dentist;
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    private LocalDate date;
    private LocalTime time;

    public Appointment(Dentist dentist, Patient patient, LocalDate date, LocalTime time) {
        this.dentist = dentist;
        this.patient = patient;
        this.date = date;
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    @Override
    public String toString() {
        return "\nAppointment:" +
                "\nDentist: " + this.dentist.getName() + " " + this.dentist.getLastName() + " (" + this.dentist.getLicenseNumber() + ")" +
                "\nPatient: " + this.patient.getName() + " " + this.patient.getLastName() + " (" + this.patient.getNationalId() + ")" +
                "\nDate: " + this.date +
                "\nTime: " + this.time;
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
