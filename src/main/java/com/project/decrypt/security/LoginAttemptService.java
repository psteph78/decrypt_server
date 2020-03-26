package com.project.decrypt.security;

import com.project.decrypt.dao.UserRepository;
import com.project.decrypt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginAttemptService {
    @Autowired
    private UserRepository userRepository;


    public void loginSucceeded(String username) {
        Optional<User> tempUser = userRepository.findByUsername(username);
        if (tempUser.isPresent()) {
            User user = tempUser.get();
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public void loginFailed(String username) {
        Optional<User> tempUser = userRepository.findByUsername(username);
        if (tempUser.isPresent()) {

        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}