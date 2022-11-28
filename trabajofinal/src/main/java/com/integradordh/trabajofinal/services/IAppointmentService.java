package com.integradordh.trabajofinal.services;


import com.integradordh.trabajofinal.models.dto.AppointmentDTO;

import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

public interface IAppointmentService {

    void saveAppointment(AppointmentDTO appointmentDTO);

    AppointmentDTO searchAppointmentById(Long id);

    Set<AppointmentDTO> searchAppointmentsByDentistLicense(String dentistLicense);

    Set<AppointmentDTO> searchAppointmentsByPatientNationalId(String nationalId);

    void updateAppointment(AppointmentDTO appointment);

    void deleteAppointment(Long id);

    Set<AppointmentDTO> searchAllAppointments();





}
