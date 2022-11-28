package com.integradordh.trabajofinal.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integradordh.trabajofinal.models.Patient;
import com.integradordh.trabajofinal.models.dto.PatientDTO;
import com.integradordh.trabajofinal.repository.IPatientRepository;
import com.integradordh.trabajofinal.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PatientServiceImpl implements IPatientService {

    @Autowired
    private IPatientRepository patientRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void savePatient(PatientDTO patientDTO) {
        Patient patient = objectMapper.convertValue(patientDTO, Patient.class);
        patientRepository.save(patient);

    }

    @Override
    public PatientDTO searchPatientById(Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);

        PatientDTO patientDTO = null;

        if (patientOptional.isPresent()) {

            patientDTO = objectMapper.convertValue(patientOptional.get(),PatientDTO.class);

        }
        return patientDTO;
    }

    @Override
    public PatientDTO searchPatientByNationalId(String nationalId) {
        Optional<Patient> patientOptional = Optional.ofNullable(patientRepository.searchPatientByNationalId(nationalId));
        PatientDTO patientDTO = null;

        if (patientOptional.isPresent()){
            patientDTO = objectMapper.convertValue(patientOptional.get(),PatientDTO.class);
        }

        return patientDTO;
    }

    @Override
    public void updatePatient(PatientDTO patientDTO) {
        Optional<Patient> patientOptional = patientRepository.findById(patientDTO.getId());
        PatientDTO patientDTOToUpdate = null;

        if(patientOptional.isPresent()){
            patientDTOToUpdate = objectMapper.convertValue(patientOptional.get(), PatientDTO.class);

        if(patientDTO.getName() != null){
            patientDTOToUpdate.setName(patientDTO.getName());
        }
        if (patientDTO.getLastName() != null){
            patientDTOToUpdate.setLastName(patientDTO.getLastName());
        }

        if (patientDTO.getRegistrationDate() != null){
            patientDTOToUpdate.setRegistrationDate(patientDTO.getRegistrationDate());
        }

        if (patientDTO.getNationalId() != null){
            patientDTOToUpdate.setNationalId(patientDTO.getNationalId());
        }
        }
            patientRepository.save(objectMapper.convertValue(patientDTOToUpdate, Patient.class));
    }

    @Override
    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
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
