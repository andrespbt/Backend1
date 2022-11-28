package com.integradordh.trabajofinal.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integradordh.trabajofinal.models.User;
import com.integradordh.trabajofinal.models.dto.UserDTO;
import com.integradordh.trabajofinal.repository.IUserRepository;
import com.integradordh.trabajofinal.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Override
    public void saveUser(UserDTO userDTO) {

        User user = objectMapper.convertValue(userDTO, User.class);
        userRepository.save(user);


    }

    @Override
    public UserDTO searchUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        UserDTO userDTO = null;

        if (userOptional.isPresent()) {

            userDTO = objectMapper.convertValue(userOptional.get(),UserDTO.class);

        }
        return userDTO;
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(userDTO.getId());
        User userToUpdate = null;

        if(userOptional.isPresent()){
            userToUpdate = objectMapper.convertValue(userOptional.get(), User.class);

            if(userDTO.getName() != null) {
                userToUpdate.setName(userDTO.getName());
            }

            if(userDTO.getLastName() != null){
                userToUpdate.setLastName(userDTO.getLastName());
            }

            userRepository.save(userToUpdate);
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
