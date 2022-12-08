package com.integradordh.trabajofinal.services;


import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.exceptions.ResourceNotFoundException;
import com.integradordh.trabajofinal.models.Appointment;
import com.integradordh.trabajofinal.models.dto.AppointmentDTO;
import com.integradordh.trabajofinal.models.dto.AppointmentDTOComplete;

import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

public interface IAppointmentService {

    void saveAppointment(Appointment appointment) throws ResourceNotFoundException, BadRequestException;

    AppointmentDTO searchAppointmentById(Long id) throws ResourceNotFoundException;

    AppointmentDTOComplete searchAppointmentDTOCompleteById(Long id) throws ResourceNotFoundException;

    Set<AppointmentDTO> searchAppointmentsByDentistLicense(String dentistLicense) throws ResourceNotFoundException;

    Set<AppointmentDTO> searchAppointmentsByPatientNationalId(String nationalId) throws ResourceNotFoundException;

    void updateAppointment(Appointment appointment) throws ResourceNotFoundException, BadRequestException;

    void deleteAppointment(Long id) throws ResourceNotFoundException;

    Set<AppointmentDTO> searchAllAppointments();





}
