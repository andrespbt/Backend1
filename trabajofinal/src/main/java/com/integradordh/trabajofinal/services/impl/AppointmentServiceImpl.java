package com.integradordh.trabajofinal.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.exceptions.ResourceNotFoundException;
import com.integradordh.trabajofinal.models.Appointment;
import com.integradordh.trabajofinal.models.dto.AppointmentDTO;
import com.integradordh.trabajofinal.models.dto.AppointmentDTOComplete;
import com.integradordh.trabajofinal.models.dto.DentistDTO;
import com.integradordh.trabajofinal.models.dto.PatientDTO;
import com.integradordh.trabajofinal.repository.IAppointmentRepository;
import com.integradordh.trabajofinal.services.IAppointmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    private static final Logger logger = LogManager.getLogger(PatientServiceImpl.class);

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DentistServiceImpl dentistService;

    @Autowired
    PatientServiceImpl patientService;


    @Override
    public void saveAppointment(Appointment appointment) throws ResourceNotFoundException, BadRequestException {
        PatientDTO patient = patientService.searchPatientByNationalId(appointment.getPatient().getNationalId());
        DentistDTO dentist = dentistService.searchDentistByLicenseNumber(appointment.getDentist().getLicenseNumber());
        if(dentist == null || patient == null){
            logger.error("Patient and dentist can't be null. Method saveAppointment.");
            throw new BadRequestException("Patient and dentist can't be null.");
        }else if (appointment.getDate() == null || appointment.getTime() == null){
            logger.error("Date and time can't be null. Method saveAppointment.");
            throw new BadRequestException("Date and time can't be null.");
        }else {
            logger.info("Appointment saved successfully. " + appointment.toString() + ". Method saveAppointment.");
            appointmentRepository.save(appointment);
        }
    }

    @Override
    public AppointmentDTO searchAppointmentById(Long id) throws ResourceNotFoundException {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        AppointmentDTO appointmentDTO = null;

        if(appointmentOptional.isPresent()){
            logger.info("Appointment with id " + id + " found successfully. Method searchAppointmentById.");
            appointmentDTO = objectMapper.convertValue(appointmentOptional, AppointmentDTO.class);
        }else {
            logger.error("Appointment with id " + id + " doesn't exists. Method searchAppointmentById.");
            throw new ResourceNotFoundException("Appointment with id " + id + " doesn't exists.");
        }
        return appointmentDTO;
    }

    @Override
    public AppointmentDTOComplete searchAppointmentDTOCompleteById(Long id) throws ResourceNotFoundException {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        AppointmentDTOComplete appointmentDTOComplete = null;

        if(appointmentOptional.isPresent()){
            logger.info("Appointment with id " + id + " found successfully. Method searchAppointmentDTOCompleteById.");
            appointmentDTOComplete = objectMapper.convertValue(appointmentOptional, AppointmentDTOComplete.class);
        }else {
            logger.error("Appointment with id " + id + " doesn't exists. Method searchAppointmentDTOCompleteById.");
            throw new ResourceNotFoundException("Appointment with id " + id + " doesn't exists.");
        }
        return appointmentDTOComplete;
    }

    @Override
    public Set<AppointmentDTO> searchAppointmentsByDentistLicense(String dentistLicense) throws ResourceNotFoundException {
        Set<Appointment> appointmentSet = appointmentRepository.searchAppointmentsByDentistLicense(dentistLicense);
         Set<AppointmentDTO> appointmentDTOS = new HashSet<>();

         if(appointmentSet != null){
             logger.info("Appointments found successfully. Method searchAppointmentsByDentistLicense.");
             for(Appointment ap : appointmentSet){
                 appointmentDTOS.add(objectMapper.convertValue(ap, AppointmentDTO.class));
             }
         }else {
             logger.error("Appointments with dentist license number " + dentistLicense + " doesn't exists. Method searchAppointmentsByDentistLicense.");
             throw new ResourceNotFoundException("Appointments with dentist license number " + dentistLicense + " doesn't exists.");
         }

         return appointmentDTOS;

    }

    @Override
    public Set<AppointmentDTO> searchAppointmentsByPatientNationalId(String nationalId) throws ResourceNotFoundException {
        Set<Appointment> appointmentSet = appointmentRepository.searchAppointmentsByPatientNationalId(nationalId);
        Set<AppointmentDTO> appointmentDTOS = new HashSet<>();

        if(appointmentSet != null){
            logger.info("Appointments found successfully. Method searchAppointmentsByPatientNationalId.");
            for(Appointment ap : appointmentSet){
                appointmentDTOS.add(objectMapper.convertValue(ap, AppointmentDTO.class));
            }
        }else {
            logger.error("Appointments with national id number " + nationalId + " doesn't exists. Method searchAppointmentsByPatientNationalId.");
            throw new ResourceNotFoundException("Appointments with national id number " + nationalId + " doesn't exists.");
        }

        return appointmentDTOS;
    }

    @Override
    public void updateAppointment(Appointment appointment) throws ResourceNotFoundException, BadRequestException {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointment.getId());
        Appointment appointmentToUpdate = null;


        if( appointmentOptional.isPresent()){
            appointmentToUpdate = appointmentOptional.get();
            if(appointmentToUpdate.equals(appointment)){
                logger.warn("Appointments are the same.");
                throw new BadRequestException("Appointments are the same.");
            }
            logger.info("Appointment with id " + appointment.getId() + " updated successfully.");
            appointmentToUpdate.merge(appointment);
            appointmentRepository.save(appointmentToUpdate);
        }else {
            logger.error("Appointment with id " + appointment.getId() + " doesn't exists. Method updateAppointment.");
            throw new ResourceNotFoundException("Appointment with id " + appointment.getId() + " doesn't exists.");
        }


    }

    @Override
    public void deleteAppointment(Long id) throws ResourceNotFoundException {
        Optional<Appointment> appointmentToDelete = appointmentRepository.findById(id);

        if(appointmentToDelete.isPresent()){
            appointmentRepository.deleteById(id);
            logger.info("Appointment with id " + id + " deleted successfully. Method deleteAppointment");
        }else {
            logger.error("Appointment with id " + id + " doesn't exists. Method deleteAppointment");
            throw new ResourceNotFoundException("Appointment with id " + id + " doesn't exists.");
        }


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
