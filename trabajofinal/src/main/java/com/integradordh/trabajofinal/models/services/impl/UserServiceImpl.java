package com.integradordh.trabajofinal.models.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.exceptions.ResourceNotFoundException;
import com.integradordh.trabajofinal.models.User;
import com.integradordh.trabajofinal.models.dto.UserDTO;
import com.integradordh.trabajofinal.models.services.IUserService;
import com.integradordh.trabajofinal.repository.IUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// TODO: Agregar loggers (info, error, etc)
// TODO: Agregar excepciones
@Service
public class UserServiceImpl implements IUserService {

    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Override
    public void saveUser(UserDTO userDTO) throws BadRequestException {

        if (userDTO.getName() == null || userDTO.getLastName() == null){
            logger.warn("Bad request. User needs name and lastname");
            throw new BadRequestException("Bad request. User needs name and lastname");
        }else {
            User user = objectMapper.convertValue(userDTO, User.class);
            userRepository.save(user);
            logger.info("User saved succesfully." + userDTO);
        }


    }

    @Override
    public UserDTO searchUserById(Long id) throws ResourceNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);

        UserDTO userDTO = null;

        if (userOptional.isPresent()) {
            userDTO = objectMapper.convertValue(userOptional.get(),UserDTO.class);
            logger.info("User found " + userDTO.toString());
        }else {
            logger.error("User with id: " + id + " doesn't exists");
            throw new ResourceNotFoundException("User with id " + id + " doesn't exists");
        }
        return userDTO;
    }

    @Override
    public void updateUser(UserDTO userDTO) throws BadRequestException, ResourceNotFoundException {
        Optional<User> userOptional = userRepository.findById(userDTO.getId());
        User userToUpdate = null;

        if(userOptional.isPresent()){
            userToUpdate = objectMapper.convertValue(userOptional.get(), User.class);
            if(userToUpdate.getName() == null && userToUpdate.getLastName() == null || userToUpdate.equals(objectMapper.convertValue(userDTO, User.class))){
                throw new BadRequestException("User is either empty or is the same than the older user");
            }

            if(userDTO.getName() != null) {
                userToUpdate.setName(userDTO.getName());
            }

            if(userDTO.getLastName() != null){
                userToUpdate.setLastName(userDTO.getLastName());
            }

            userRepository.save(userToUpdate);
        }else {
            logger.error("User with id " + userDTO.getId() + " doesn't exists");
            throw new ResourceNotFoundException("User with id " + userDTO.getId() + " doesn't exists");
        }
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Set<UserDTO> searchAllUsers() {

        List<User> users = userRepository.findAll();

        Set<UserDTO> usersDTO = new HashSet<>();

        for(User user : users) {

            usersDTO.add(objectMapper.convertValue(user, UserDTO.class));
        }

        return usersDTO;

    }
}