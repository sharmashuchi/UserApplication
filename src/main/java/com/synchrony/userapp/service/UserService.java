package com.synchrony.userapp.service;

import com.synchrony.userapp.domain.User;
import com.synchrony.userapp.dto.UserDTO;
import com.synchrony.userapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String registerUser(UserDTO userDTO) {
        String message = "User successfully registered";
        try {
            User user = new User();
            user.setCreatedOn(userDTO.getCreatedOn());
            user.setPassword(userDTO.getPassword());
            user.setUserId(userDTO.getUserId());
            user.setUserName(userDTO.getUserName());
            userRepository.save(user);
        } catch (Exception e) {
            message = "Unable to register user. Please check user details.";
        }
        return message;
    }

    public String checkUserIdAvailable(String userId) {
        Optional userExists = userRepository.findById(userId);
        String message = userExists.isPresent()? "Id already used": "Id available";
        return message;
    }
}
