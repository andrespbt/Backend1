package com.integradordh.trabajofinal.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integradordh.trabajofinal.models.Appointment;
import com.integradordh.trabajofinal.models.Dentist;
import com.integradordh.trabajofinal.models.Patient;
import com.integradordh.trabajofinal.models.dto.AppointmentDTO;
import com.integradordh.trabajofinal.repository.IAppointmentRepository;
import com.integradordh.trabajofinal.services.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DentistServiceImpl dentistService;

    @Autowired
    PatientServiceImpl patientService;


    @Override
    public void saveAppointment(AppointmentDTO appointmentDTO) {
        appointmentRepository.save(objectMapper.convertValue(appointmentDTO, Appointment.class));
    }

    @Override
    public AppointmentDTO searchAppointmentById(Long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        AppointmentDTO appointmentDTO = null;

        if(appointmentOptional.isPresent()){
            appointmentDTO = objectMapper.convertValue(appointmentOptional, AppointmentDTO.class);
        }
        return appointmentDTO;
    }

    @Override
    public Set<AppointmentDTO> searchAppointmentsByDentistLicense(String dentistLicense) {
        Set<Appointment> appointmentSet = appointmentRepository.searchAppointmentsByDentistLicense(dentistLicense);
         Set<AppointmentDTO> appointmentDTOS = new HashSet<>();

         if(appointmentSet != null){
             for(Appointment ap : appointmentSet){
                 appointmentDTOS.add(objectMapper.convertValue(ap, AppointmentDTO.class));
             }
         }

         return appointmentDTOS;

    }

    @Override
    public Set<AppointmentDTO> searchAppointmentsByPatientNationalId(String nationalId) {
        Set<Appointment> appointmentSet = appointmentRepository.searchAppointmentsByPatientNationalId(nationalId);
        Set<AppointmentDTO> appointmentDTOS = new HashSet<>();

        if(appointmentSet != null){
            for(Appointment ap : appointmentSet){
                appointmentDTOS.add(objectMapper.convertValue(ap, AppointmentDTO.class));
            }
        }

        return appointmentDTOS;
    }

    @Override
    public void updateAppointment(AppointmentDTO appointment) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointment.getId());
        Appointment appointmentToUpdate = null;


        if( appointmentOptional.isPresent()){
            appointmentToUpdate = appointmentOptional.get();
            if(appointment.getDentist() != null){
                appointmentToUpdate.setDentist(objectMapper.convertValue(dentistService.searchDentistByLicenseNumber(appointment.getDentist().getLicenseNumber()), Dentist.class));
            }

            if(appointment.getPatient() != null){
                appointmentToUpdate.setPatient(objectMapper.convertValue(patientService.searchPatientByNationalId(appointment.getPatient().getNationalId()), Patient.class));
            }

            if(appointment.getTime() != null){
                appointmentToUpdate.setTime(appointment.getTime());
            }

            if(appointment.getDate() != null) {
                appointmentToUpdate.setDate(appointment.getDate());
            }
            appointmentRepository.save(appointmentToUpdate);
        }


    }

    @Override
    public void deleteAppointment(Long id) {

        appointmentRepository.deleteById(id);

    }

    @Override
    public Set<AppointmentDTO> searchAllAppointments() {
        List<Appointment> appointmentList = appointmentRepository.findAll();

        Set<AppointmentDTO> appointmentsDTOSet = new HashSet<>();

        for(Appointment ap : appointmentList){
            appointmentsDTOSet.add(objectMapper.convertValue(ap, AppointmentDTO.class));
        }
        return appointmentsDTOSet;
    }
}
