package com.integradordh.trabajofinal.controllers;

import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.exceptions.ResourceNotFoundException;
import com.integradordh.trabajofinal.models.Patient;
import com.integradordh.trabajofinal.models.dto.PatientDTO;
import com.integradordh.trabajofinal.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/patients")
public class PatientController {


    @Autowired
    IPatientService patientService;

    @PostMapping
    public ResponseEntity<?> savePatient(@RequestBody Patient patient) throws BadRequestException {
        ResponseEntity<?> response = null;
        try{
            patientService.savePatient(patient);
            response = ResponseEntity.status(HttpStatus.CREATED).body("Patient saved successfully " + patient.toString());

        }catch (BadRequestException e){
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient couldn't be created.");
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> searchPatientById(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<?> response = null;
        try {
            PatientDTO patientDTO = patientService.searchPatientById(id);
            response = ResponseEntity.status(HttpStatus.FOUND).body(patientDTO);

        }catch (ResourceNotFoundException e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found.");
            e.printStackTrace();

        }
        return response;
    }

    @GetMapping("/nationalId/{nationalId}")
    public ResponseEntity<?> searchPatientByNationalId(@PathVariable String nationalId) throws ResourceNotFoundException {
        ResponseEntity<?> response = null;
        try {
            PatientDTO patientDTO = patientService.searchPatientByNationalId(nationalId);
            response = ResponseEntity.status(HttpStatus.FOUND).body(patientDTO);
        }catch (ResourceNotFoundException e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found.");
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping
    public Set<PatientDTO> searchAllPatients(){

        return patientService.searchAllPatients();
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updatePatient(@RequestBody Patient patient) throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<?> response = null;

        try {
            patientService.updatePatient(patient);
            response = ResponseEntity.status(HttpStatus.OK).body("Patient updated successfully.");

        }catch (BadRequestException e){
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient couldn't be updated ");
            e.printStackTrace();

        }

        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePatientById(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<?> response = null;

        try {
        String patientInfo = patientService.searchPatientById(id).toString();
        patientService.deletePatientById(id);
        response = ResponseEntity.ok().body("Patient deleted successfully" + patientInfo);

        }catch (ResourceNotFoundException e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient couldn't be deleted");
            e.printStackTrace();
        }
        return response;
    }

}
