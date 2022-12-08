package com.integradordh.trabajofinal.services;

import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.exceptions.ResourceNotFoundException;
import com.integradordh.trabajofinal.models.Dentist;
import com.integradordh.trabajofinal.models.dto.DentistDTO;
import com.integradordh.trabajofinal.models.dto.DentistDTOComplete;

import java.util.Set;

public interface IDentistService {

    void saveDentist(Dentist dentist) throws BadRequestException;

    DentistDTO searchDentistById(Long id) throws ResourceNotFoundException;

    DentistDTO searchDentistByLicenseNumber(String licenseNumber) throws ResourceNotFoundException;

    DentistDTOComplete searchDentistDTOCompleteByLicenseNumber(String licenseNumber) throws ResourceNotFoundException;

    void updateDentist(Dentist dentist) throws BadRequestException, ResourceNotFoundException;

    void deleteDentistById(Long id) throws ResourceNotFoundException;

    Set<DentistDTO> searchAllDentists();


}
