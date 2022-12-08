package com.integradordh.trabajofinal.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.exceptions.ResourceNotFoundException;
import com.integradordh.trabajofinal.models.Patient;
import com.integradordh.trabajofinal.models.dto.PatientDTO;
import com.integradordh.trabajofinal.models.dto.PatientDTOComplete;
import com.integradordh.trabajofinal.repository.IPatientRepository;
import com.integradordh.trabajofinal.services.IPatientService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientServiceImpl implements IPatientService {

    private static final Logger logger = LogManager.getLogger(PatientServiceImpl.class);

    @Autowired
    private IPatientRepository patientRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void savePatient(Patient patient) throws BadRequestException {

        if(patientRepository.searchPatientByNationalId(patient.getNationalId()).isEmpty()){
            logger.info("Patient saved succesfully. " + patient.toString() + ". Method savePatient.");
            patientRepository.save(patient);
        }else {
            logger.warn("Patient with national id " + patient.getNationalId() + " already exists. Method savePatient");
            throw new BadRequestException("Patient with national id " + patient.getNationalId() + " already exists.");
        }


    }

    @Override
    public PatientDTO searchPatientById(Long id) throws ResourceNotFoundException {
        Optional<Patient> patientOptional = patientRepository.findById(id);

        PatientDTO patientDTO = null;

        if (patientOptional.isPresent()) {
            logger.info("Patient with id " + id + " found successfully. Method searchPatientById" );
            patientDTO = objectMapper.convertValue(patientOptional.get(),PatientDTO.class);
        }else {
            logger.error("Patient with id " + id + " doesn't exists. Method searchPatientById");
            throw new ResourceNotFoundException("Patient with id " + id+ " doesn't exists.");
        }
        return patientDTO;
    }

    @Override
    public PatientDTOComplete searchPatientCompleteByNationalId(String nationalId) throws ResourceNotFoundException {
        Optional<Patient> patientDTOCompleteOptional = patientRepository.searchPatientByNationalId(nationalId);

        PatientDTOComplete patientDTOComplete = null;

        if(patientDTOCompleteOptional.isPresent()){
            logger.info("Patient with national id " + nationalId + " found successfully. Method searchPatientCompleteByNationalId" );
            patientDTOComplete = objectMapper.convertValue(patientDTOCompleteOptional.get(),PatientDTOComplete.class);
        }else {
            logger.error("Patient with national id " + nationalId + " doesn't exists. Method searchPatientCompleteByNationalId");
            throw new ResourceNotFoundException("Patient national id " + nationalId + " doesn't exists.");

        }
        return patientDTOComplete;
    }

    @Override
    public PatientDTO searchPatientByNationalId(String nationalId) throws ResourceNotFoundException {
        Optional<Patient> patientOptional = patientRepository.searchPatientByNationalId(nationalId);
        PatientDTO patientDTO = null;

        if (patientOptional.isPresent()){
            logger.info("Patient with national id " + nationalId + " found successfully. Method searchPatientByNationalId" );
            patientDTO = objectMapper.convertValue(patientOptional.get(),PatientDTO.class);
        }else {
            logger.error("Patient with national id " + nationalId + " doesn't exists. Method updatePatient");
            throw new ResourceNotFoundException("Patient with national id " + nationalId+ " doesn't exists.");
        }

        return patientDTO;
    }

    @Override
    public void updatePatient(Patient patient) throws BadRequestException, ResourceNotFoundException {
        Optional<Patient> patientToUpdateOptional = patientRepository.findById(patient.getId());

        if(patientToUpdateOptional.isPresent()){
            Patient patientToUpdate = patientToUpdateOptional.get();
            if( patientToUpdate.equals(patient)){
                logger.warn("Trying to update patient with the same values. Method updatePatient");
                throw new BadRequestException("Patients are the same.");
            }

            patientToUpdate.merge(patient);

            patientRepository.save(patientToUpdate);
        }else {
            logger.error("Patient with national id " + patient.getNationalId() + " doesn't exists. Method updatePatient");
            throw new ResourceNotFoundException("Patient with national id " + patient.getNationalId() + " doesn't exists.");
        }

    }

    @Override
    public void deletePatientById(Long id) throws ResourceNotFoundException {
        Optional<Patient> patientOptional = patientRepository.findById(id);

        if(patientOptional.isPresent()){
        patientRepository.deleteById(id);
        logger.info("Patient with id " + patientOptional.get().getId() + " deleted succesfully. Method deletePatientById");
        }else {
            logger.error("Patient with id " + id + " doesn't exists. Method deletePatientById");
            throw new ResourceNotFoundException("Patient with id " + id + " doesn't exists.");
        }
    }

    @Override
    public Set<PatientDTO> searchAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        Set<PatientDTO> patientsDTO = new HashSet<>();

        for(Patient patient : patients) {

            patientsDTO.add(objectMapper.convertValue(patient, PatientDTO.class));
        }

        return patientsDTO;
    }
}
