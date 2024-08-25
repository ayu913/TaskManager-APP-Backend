package com.example.task_manager.controller;
import com.example.task_manager.model.User;
import com.example.task_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            Optional<User> userExist = userService.findByEmail(user.getEmail());

            if (userExist.isPresent()) {
                return ResponseEntity.badRequest().body("User already exists");
            }

            User newUser = userService.registerUser(user);
            newUser.setPassword(null);

            if (newUser.getIsAdmin()) {
                String token = userService.generateJwtToken(newUser);
                return ResponseEntity.status(201).header("Authorization", "Bearer " + token).body(newUser);
            }

            return ResponseEntity.status(201).body(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
