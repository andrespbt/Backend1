package com.integradordh.trabajofinal.controllers;

import com.integradordh.trabajofinal.models.dto.DentistDTO;
import com.integradordh.trabajofinal.services.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/dentists")
public class DentistController {

    @Autowired
    IDentistService dentistService;

    @PostMapping
    public ResponseEntity<?> saveDentist(@RequestBody DentistDTO dentistDTO){
        dentistService.saveDentist(dentistDTO);
        return ResponseEntity.ok().body("Dentist created succesfully" + dentistDTO.toString());
    }

    @GetMapping("/id/{id}")
    public DentistDTO searchDentistById(@PathVariable Long id){
        return dentistService.searchDentistById(id);
    }

    @GetMapping("/licenseNumber/{licenseNumber}")
    public DentistDTO searchDentistByNationalId(@PathVariable String licenseNumber){
        return dentistService.searchDentistByLicenseNumber(licenseNumber);
    }

    @GetMapping
    public Set<DentistDTO> searchAllDentists(){
        return dentistService.searchAllDentists();
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateDentist(@RequestBody DentistDTO dentistDTO){
        dentistService.updateDentist(dentistDTO);
        return ResponseEntity.ok().body("Dentist updated succesfully" + dentistService.searchDentistById(dentistDTO.getId()).toString());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDentistById(@PathVariable Long id) {
        String dentistInfo = dentistService.searchDentistById(id).toString();
        dentistService.deleteDentistById(id);
        return ResponseEntity.ok().body("Dentist deleted succesfully" + dentistInfo);
    }

}
