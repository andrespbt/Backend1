package com.integradordh.trabajofinal.models.services;

import com.integradordh.trabajofinal.models.dto.DentistDTO;

import java.util.Set;

public interface IDentistService {

    void saveDentist(DentistDTO dentistDTO);

    DentistDTO searchDentistById(Long id);

    DentistDTO searchDentistByLicenseNumber(String licenseNumber);

    void updateDentist(DentistDTO dentistDTO);

    void deleteDentistById(Long id);

    Set<DentistDTO> searchAllDentists();


}
