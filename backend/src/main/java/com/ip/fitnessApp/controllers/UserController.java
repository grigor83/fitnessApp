package com.ip.fitnessApp.controllers;

import com.ip.fitnessApp.model.User;
import com.ip.fitnessApp.service.EmailService;
import com.ip.fitnessApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public UserController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam("id") String id) {
        User user = userService.findById(Integer.valueOf(id));
        if (user != null){
            user.setVerified(true);
            userService.updateUser(user, user.getId());
            return ResponseEntity.ok("Email verified successfully.");
        }
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        if (user.getId() == 0){
            User newUser = userService.createUser(user);
            if (!newUser.isCouncelor())
                emailService.sendVerificationEmail(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        else {
            // U slucaju da je user registrovan ali jos nije aktivirao svoj nalog, treba mu opet poslati email
            emailService.sendVerificationEmail(user);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        User updatedUser = userService.updateUser(user, id);

        if (updatedUser != null)
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

}
