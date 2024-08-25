package com.example.task_manager.service;

import com.example.task_manager.model.User;
import com.example.task_manager.repository.UserRepository;
import com.example.task_manager.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public  User registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String generateJwtToken(User user) {
        return jwtUtil.generateToken(user.getId());
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
