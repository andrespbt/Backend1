package com.integradordh.trabajofinal.controllers;

import com.integradordh.trabajofinal.models.dto.PatientDTO;
import com.integradordh.trabajofinal.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/patients")
public class PatientController {


    @Autowired
    IPatientService patientService;

    @PostMapping
    public ResponseEntity<?> savePatient(@RequestBody PatientDTO patientDTO){
        patientService.savePatient(patientDTO);
        return ResponseEntity.ok().body("Patient created succesfully" + patientDTO.toString());
    }

    @GetMapping("/id/{id}")
    public PatientDTO searchPatientById(@PathVariable Long id){
        return patientService.searchPatientById(id);
    }

    @GetMapping("/nationalId/{nationalId}")
    public PatientDTO searchPatientByNationalId(@PathVariable String nationalId){
        return patientService.searchPatientByNationalId(nationalId);
    }

    @GetMapping
    public Set<PatientDTO> searchAllPatients(){
        return patientService.searchAllPatients();
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updatePatient(@RequestBody PatientDTO patientDTO){
        patientService.updatePatient(patientDTO);

        return ResponseEntity.ok().body("Patient updated succesfully" + patientService.searchPatientById(patientDTO.getId()).toString());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePatientById(@PathVariable Long id) {
        String patientInfo = patientService.searchPatientById(id).toString();
        patientService.deletePatientById(id);
        return ResponseEntity.ok().body("Patient deleted succesfully" + patientInfo);
    }

}
