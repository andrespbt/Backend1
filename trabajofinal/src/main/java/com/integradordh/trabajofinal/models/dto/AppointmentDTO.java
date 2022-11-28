package com.integradordh.trabajofinal.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

    private Long id;
    private DentistDTO dentist;
    private PatientDTO patient;
    private String date;
    private LocalTime time;


    public AppointmentDTO(DentistDTO dentist, PatientDTO patient, String date, LocalTime time) {
        this.dentist = dentist;
        this.patient = patient;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "\nDentist: " + dentist.toString()+ "\n" +
                "\nPatient: " + patient.toString()+ "\n" +
                "\nDate = " + date +
                "\nTime = " + time;
    }


}
