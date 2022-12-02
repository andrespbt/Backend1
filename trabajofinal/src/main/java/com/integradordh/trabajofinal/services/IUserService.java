package com.integradordh.trabajofinal.services;

import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.models.dto.UserDTO;

import java.util.Set;

public interface IUserService {

    void saveUser(UserDTO userDTO);

    UserDTO searchUserById(Long id) throws BadRequestException;

    void updateUser(UserDTO userDTO);

    void deleteUserById(Long id);

    Set<UserDTO> searchAllUsers();

}
