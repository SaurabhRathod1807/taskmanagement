package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepositoryRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(String username, String password) {
        User existingUser = (User) userRepositoryRepo.findByUsername(username);
        if (existingUser != null) {
            throw new RuntimeException("User already exists");
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        return userRepositoryRepo.save(newUser);
    }

    public User authenticateUser(String username, String password) {
        User user = (User) userRepositoryRepo.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return user;
    }

    public Optional<User> getUserById(int id) {
        return userRepositoryRepo.findById(id);
    }
}
