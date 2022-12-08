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
public class AppointmentDTOComplete {
    private Long id;
    private DentistDTOComplete dentist;
    private PatientDTOComplete patient;
    private LocalDate date;
    private LocalTime time;
}
