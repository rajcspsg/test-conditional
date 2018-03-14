package com.as.demo.service;

import com.as.demo.entity.User;
import com.as.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired(required = false)
    private UserRepository userRepository;

    public void saveUser(User user) {
        if(userRepository == null) {
            System.out.println("userRepository == null");
            return;
        }
        userRepository.save(user);
    }
}
