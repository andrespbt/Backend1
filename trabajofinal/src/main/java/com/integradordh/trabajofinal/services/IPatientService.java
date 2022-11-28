package com.integradordh.trabajofinal.services;

import com.integradordh.trabajofinal.models.dto.PatientDTO;

import java.util.Set;

public interface IPatientService {

    void savePatient(PatientDTO patientDTO);

    PatientDTO searchPatientById(Long id);

    PatientDTO searchPatientByNationalId(String nationalId);

    void updatePatient(PatientDTO patientDTO);

    void deletePatientById(Long id);

    Set<PatientDTO> searchAllPatients();
}
