package main.com.dh.integrador.model;
import java.sql.*;

public class Appointment {

    private Long id;
    private Dentist dentist;
    private Patient patient;
    private Date date;
    private Time time;

    public Appointment() {
    }

    public Appointment(Long id,Dentist dentist, Patient patient, Date date, Time time) {
        this.id = id;
        this.dentist = dentist;
        this.patient = patient;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "\nAppointment:" + "\n" + "ID: " + id + "\n" + "\n" + patient.toString() + "\n" + "\n" + dentist.toString()+ "\n" + "\nDate: " + date + "\nHour: " + time;
    }
}
