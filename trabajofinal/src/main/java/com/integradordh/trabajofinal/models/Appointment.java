package com.integradordh.trabajofinal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "appointments_tb")
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
    private String date;
    private LocalTime time;

    public Appointment(Dentist dentist, Patient patient, Date date, LocalTime time) {
        this.dentist = dentist;
        this.patient = patient;
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        this.date = DateFor.format(date);
        this.time = time;
    }

    public Appointment(Dentist dentist, Patient patient, String date, LocalTime time) {
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


}
