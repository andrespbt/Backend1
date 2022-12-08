package com.integradordh.trabajofinal.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.exceptions.ResourceNotFoundException;
import com.integradordh.trabajofinal.models.Dentist;
import com.integradordh.trabajofinal.models.dto.DentistDTO;
import com.integradordh.trabajofinal.models.dto.DentistDTOComplete;
import com.integradordh.trabajofinal.services.IDentistService;
import com.integradordh.trabajofinal.repository.IDentistRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DentistServiceImpl implements IDentistService {

    private static final Logger logger = LogManager.getLogger(PatientServiceImpl.class);


    @Autowired
    private IDentistRepository dentistRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Override
    public void saveDentist(Dentist dentist) throws BadRequestException {
        if(dentistRepository.searchDentistByLicenseNumber(dentist.getLicenseNumber()).isEmpty()){
            logger.info("Dentist saved succesfully. " + dentist.toString() + ". Method saveDentist.");
            dentistRepository.save(dentist);
        }else {
            logger.warn("Dentist with license number " + dentist.getLicenseNumber() + " already exists. Method saveDentist");
            throw new BadRequestException("Dentist with license number " + dentist.getLicenseNumber() + " already exists.");
        }
    }

    @Override
    public DentistDTO searchDentistById(Long id) throws ResourceNotFoundException {

        Optional<Dentist> dentistOptional = dentistRepository.findById(id);
        DentistDTO dentistDTO = null;

        if(dentistOptional.isPresent()){
            logger.info("Dentist with id " + id + " found successfully. Method searchDentistById");
            dentistDTO = objectMapper.convertValue(dentistOptional.get(), DentistDTO.class);
        }else {
            logger.error("Dentist with id " + id + " doesn't exists. Method searchDentistById");
            throw new ResourceNotFoundException("Dentist with id " + id + " doesn't exists");
        }
        return dentistDTO;
    }

    @Override
    public DentistDTO searchDentistByLicenseNumber(String licenseNumber) throws ResourceNotFoundException {
        Optional<Dentist> dentistOptional= dentistRepository.searchDentistByLicenseNumber(licenseNumber);
        DentistDTO dentistDTO = null;

        if(dentistOptional.isPresent()){
            logger.info("Dentist with license number " + licenseNumber + " found successfully. Method searchDentistByLicenseNumber");
            dentistDTO = objectMapper.convertValue(dentistOptional.get(), DentistDTO.class);
        }else {
            logger.error("Dentist with license number " + licenseNumber + " doesn't exists. Method searchDentistByLicenseNumber");
            throw new ResourceNotFoundException("Dentist with license number " + licenseNumber + " doesn't exists");
        }

        return dentistDTO;
    }

    @Override
    public DentistDTOComplete searchDentistDTOCompleteByLicenseNumber(String licenseNumber) throws ResourceNotFoundException {
        Optional<Dentist> dentistOptional= dentistRepository.searchDentistByLicenseNumber(licenseNumber);
        DentistDTOComplete dentistDTOComplete = null;

        if(dentistOptional.isPresent()){
            logger.info("Dentist with license number " + licenseNumber + " found successfully. Method searchDentistDTOCompleteByLicenseNumber");
            dentistDTOComplete = objectMapper.convertValue(dentistOptional.get(), DentistDTOComplete.class);
        }else {
            logger.error("Dentist with license number " + licenseNumber + " doesn't exists. Method searchDentistDTOCompleteByLicenseNumber");
            throw new ResourceNotFoundException("Dentist with license number " + licenseNumber + " doesn't exists");
        }

        return dentistDTOComplete;
    }

    @Override
    public void updateDentist(Dentist dentist) throws BadRequestException, ResourceNotFoundException {

        Optional<Dentist> dentistToUpdateOptional = dentistRepository.findById(dentist.getId());

        if(dentistToUpdateOptional.isPresent()){
            Dentist dentistToUpdate = objectMapper.convertValue(dentistToUpdateOptional.get(), Dentist.class);
            if(dentist.equals(dentistToUpdate)){
                logger.warn("Trying to update dentist with the same values. Method updateDentist.");
                throw new BadRequestException("Dentists are the same.");
            }
            logger.info("Dentist with id " + dentist.getId() + " updated successfully.  Method updateDentist.");
            dentistToUpdate.merge(dentist);
            dentistRepository.save(dentistToUpdate);
        }else {
            logger.error("Dentist with id " + dentist.getId() + " doesn't exists. Method updateDentist.");
            throw new ResourceNotFoundException("Dentist with id " + dentist.getId() + " doesn't exists.");
        }

    }

    @Override
    public void deleteDentistById(Long id) throws ResourceNotFoundException {
        Optional<Dentist> dentistToDelete = dentistRepository.findById(id);
        if(dentistToDelete.isPresent()){
            dentistRepository.deleteById(id);
            logger.info("Dentist with id " + dentistToDelete.get().getId() + " deleted successfully. Method deleteDentistById");
        }else {
            logger.error("Dentist with id " + id + " doesn't exists. Method deleteDentistById");
            throw new ResourceNotFoundException("Dentist with id " + id + " doesn't exists.");
        }
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
