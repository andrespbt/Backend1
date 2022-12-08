package com.integradordh.trabajofinal.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private DentistDTO dentist;
    private PatientDTO patient;
    private LocalDate date;
    private LocalTime time;


    @Override
    public String toString() {
        return "\nAppointment:" +
                "\nDentist: " + this.dentist.getName() + " " + this.dentist.getLastName() + " ( " + this.dentist.getLicenseNumber() + " )" +
                "\nPatient: " + this.patient.getName() + " " + this.patient.getLastName() + " (" + this.patient.getNationalId() + " )" +
                "\nDate: " + this.date +
                "\nTime: " + this.time;
    }


}
