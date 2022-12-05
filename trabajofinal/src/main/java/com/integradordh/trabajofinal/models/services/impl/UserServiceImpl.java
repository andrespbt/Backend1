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
            logger.warn("Bad request. User needs name and lastname.");
            throw new BadRequestException("Bad request. User needs name and lastname.");
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
            logger.error("User with id: " + id + " doesn't exists.");
            throw new ResourceNotFoundException("User with id " + id + " doesn't exists.");
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
                throw new BadRequestException("User is either empty or is the same than the older user.");
            }

            if(userDTO.getName() != null) {
                userToUpdate.setName(userDTO.getName());
            }

            if(userDTO.getLastName() != null){
                userToUpdate.setLastName(userDTO.getLastName());
            }
            logger.info("User with id " + userDTO.getId() + " updated succesfully. Method: updateUser");
            userRepository.save(userToUpdate);
        }else {
            logger.error("User with id " + userDTO.getId() + " doesn't exists. Method: updateUser");
            throw new ResourceNotFoundException("User with id " + userDTO.getId() + " doesn't exists.");
        }
    }

    @Override
    public void deleteUserById(Long id) throws ResourceNotFoundException {
        if( userRepository.findById(id).isEmpty()){
            logger.error("User with id " + id + " doesn't exists. Method: deleteUserById");
            throw new ResourceNotFoundException("User with id " + id + " doesn't exists.");
        }else {
            logger.info("User deleted " + userRepository.findById(id) + ". Method: deleteUserById");
            userRepository.deleteById(id);
        }

    }

    @Override
    public Set<UserDTO> searchAllUsers() throws ResourceNotFoundException {

        List<User> users = userRepository.findAll();

        Set<UserDTO> usersDTO = new HashSet<>();

        for(User user : users) {

            usersDTO.add(objectMapper.convertValue(user, UserDTO.class));
        }

        if( usersDTO.size() == 0){
            logger.error("There aren't any users. Method: searchAllUsers");
            throw new ResourceNotFoundException("There aren't any users.");
        }else {
            logger.info("Showing all users. Method: searchAllUsers");
            return usersDTO;
        }



    }
}
