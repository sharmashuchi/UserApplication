package com.sample.userapp.service;

import com.sample.userapp.domain.User;
import com.sample.userapp.dto.UserDTO;
import com.sample.userapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

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
        Optional<User> userExists = userRepository.findById(userId);
        String message = userExists.isPresent()? userExists.get().getUserId(): "Id available";
        return message;
    }

    public User getUserDetails(String userId) {
        Optional<User> userExists = userRepository.findById(userId);
        return userExists.isPresent()? userExists.get(): null;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<SimpleGrantedAuthority> roleList = new ArrayList<>();
            return new org.springframework.security.core.userdetails.User(user.get().getUserId(), user.get().getPassword(), roleList);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + userId);
        }
    }
}
