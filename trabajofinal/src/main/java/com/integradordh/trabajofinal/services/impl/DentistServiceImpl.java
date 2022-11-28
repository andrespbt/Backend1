package com.integradordh.trabajofinal.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integradordh.trabajofinal.models.Dentist;
import com.integradordh.trabajofinal.models.dto.DentistDTO;
import com.integradordh.trabajofinal.repository.IDentistRepository;
import com.integradordh.trabajofinal.services.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DentistServiceImpl implements IDentistService {


    @Autowired
    private IDentistRepository dentistRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Override
    public void saveDentist(DentistDTO dentistDTO) {
        Dentist dentist = objectMapper.convertValue(dentistDTO , Dentist.class);

        if(dentistRepository.searchDentistByLicenseNumber(dentistDTO.getLicenseNumber()) == null)
       dentistRepository.save(dentist);
    }

    @Override
    public DentistDTO searchDentistById(Long id) {

        Optional<Dentist> dentistOptional = dentistRepository.findById(id);
        DentistDTO dentistDTO = null;

        if(dentistOptional.isPresent()){
            dentistDTO = objectMapper.convertValue(dentistOptional.get(), DentistDTO.class);
        }
        return dentistDTO;
    }

    @Override
    public DentistDTO searchDentistByLicenseNumber(String licenseNumber) {
        Optional<Dentist> dentistOptional= Optional.ofNullable(dentistRepository.searchDentistByLicenseNumber(licenseNumber));
        DentistDTO dentistDTO = null;

        if(dentistOptional.isPresent()){
            dentistDTO = objectMapper.convertValue(dentistOptional.get(), DentistDTO.class);
        }

        return dentistDTO;
    }

    @Override
    public void updateDentist(DentistDTO dentistDTO) {

        Optional<Dentist> dentistOptional = dentistRepository.findById(dentistDTO.getId());
        DentistDTO dentistDTOToUpdate = null;

        if(dentistOptional.isPresent()){
            dentistDTOToUpdate = objectMapper.convertValue(dentistOptional.get(), DentistDTO.class);

            if(dentistDTO.getName() != null){
                dentistDTOToUpdate.setName(dentistDTO.getName());
            }
            if (dentistDTO.getLastName() != null){
                dentistDTOToUpdate.setLastName(dentistDTO.getLastName());
            }

            if (dentistDTO.getLicenseNumber() != null){
                dentistDTOToUpdate.setLicenseNumber(dentistDTO.getLicenseNumber());
            }
        }
        dentistRepository.save(objectMapper.convertValue(dentistDTOToUpdate, Dentist.class));

    }

    @Override
    public void deleteDentistById(Long id) {
        dentistRepository.deleteById(id);
    }


    @Override
    public Set<DentistDTO> searchAllDentists() {
       List<Dentist> dentistsList = dentistRepository.findAll();

       Set<DentistDTO> dentistsDTOList = new HashSet<>();

       for( Dentist dentist : dentistsList){
           dentistsDTOList.add(objectMapper.convertValue(dentist, DentistDTO.class));
       }

       return dentistsDTOList;
    }
}
