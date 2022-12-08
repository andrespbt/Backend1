package com.integradordh.trabajofinal.services;

import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.models.AppUser;

public interface IUserService {

    void saveUser(AppUser userDTO) throws BadRequestException;

}
