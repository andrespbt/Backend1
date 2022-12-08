package com.integradordh.trabajofinal.services;

import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.exceptions.ResourceNotFoundException;
import com.integradordh.trabajofinal.models.Patient;
import com.integradordh.trabajofinal.models.dto.PatientDTO;
import com.integradordh.trabajofinal.models.dto.PatientDTOComplete;

import java.util.Set;

public interface IPatientService {

    void savePatient(Patient patientDTO) throws BadRequestException;

    PatientDTO searchPatientById(Long id) throws ResourceNotFoundException;

    PatientDTOComplete searchPatientCompleteByNationalId(String nationalId) throws ResourceNotFoundException;

    PatientDTO searchPatientByNationalId(String nationalId) throws ResourceNotFoundException;

    void updatePatient(Patient patient) throws BadRequestException, ResourceNotFoundException;

    void deletePatientById(Long id) throws ResourceNotFoundException;

    Set<PatientDTO> searchAllPatients();
}
