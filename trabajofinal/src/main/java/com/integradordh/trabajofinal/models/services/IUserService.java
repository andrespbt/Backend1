package com.integradordh.trabajofinal.models.services;

import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.exceptions.ResourceNotFoundException;
import com.integradordh.trabajofinal.models.dto.UserDTO;

import java.util.Set;

public interface IUserService {

    void saveUser(UserDTO userDTO) throws BadRequestException;

    UserDTO searchUserById(Long id) throws ResourceNotFoundException;

    void updateUser(UserDTO userDTO) throws ResourceNotFoundException, BadRequestException;

    void deleteUserById(Long id) throws ResourceNotFoundException;

    Set<UserDTO> searchAllUsers() throws ResourceNotFoundException;

}
