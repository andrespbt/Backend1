package com.integradordh.trabajofinal.controllers;

import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.exceptions.ResourceNotFoundException;
import com.integradordh.trabajofinal.models.Dentist;
import com.integradordh.trabajofinal.models.dto.DentistDTO;
import com.integradordh.trabajofinal.services.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/dentists")
public class DentistController {

    @Autowired
    IDentistService dentistService;

    @PostMapping
    public ResponseEntity<?> saveDentist(@RequestBody Dentist dentist) throws BadRequestException {
        ResponseEntity<?> response = null;
        try{
            dentistService.saveDentist(dentist);
            response = ResponseEntity.status(HttpStatus.CREATED).body("Dentist created successfully " + dentist.toString());

        }catch (BadRequestException e){
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dentist couldn't be created.");
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> searchDentistById(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<?> response = null;
        try{
            DentistDTO dentistDTO = dentistService.searchDentistById(id);
            response = ResponseEntity.status(HttpStatus.FOUND).body(dentistDTO);

        }catch (ResourceNotFoundException e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dentist not found.");
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/licenseNumber/{licenseNumber}")
    public ResponseEntity<?> searchDentistByNationalId(@PathVariable String licenseNumber) throws ResourceNotFoundException {
        ResponseEntity<?> response = null;
        try{
            DentistDTO dentistDTO = dentistService.searchDentistByLicenseNumber(licenseNumber);
            response = ResponseEntity.status(HttpStatus.FOUND).body(dentistDTO);

        }catch (ResourceNotFoundException e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dentist not found.");
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping
    public Set<DentistDTO> searchAllDentists(){

        return dentistService.searchAllDentists();
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateDentist(@RequestBody Dentist dentist) throws ResourceNotFoundException, BadRequestException {
        ResponseEntity<?> response = null;
        try{
            dentistService.updateDentist(dentist);
            DentistDTO dentistDTOInfo = dentistService.searchDentistById(dentist.getId());
            response = ResponseEntity.status(HttpStatus.OK).body("Dentist updated. " + dentistDTOInfo.toString());

        }catch (ResourceNotFoundException e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dentist couldn't be updated.");
            e.printStackTrace();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDentistById(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<?> response = null;

        try {
        String dentistInfo = dentistService.searchDentistById(id).toString();
        dentistService.deleteDentistById(id);
        response = ResponseEntity.ok().body("Dentist deleted successfully" + dentistInfo);

        }catch (ResourceNotFoundException e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dentist couldn't be deleted.");
            e.printStackTrace();

        }
        return response;
    }

}
