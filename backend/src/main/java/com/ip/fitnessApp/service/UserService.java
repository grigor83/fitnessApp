package com.ip.fitnessApp.service;

import com.ip.fitnessApp.exceptions.RecordNotFoundException;
import com.ip.fitnessApp.exceptions.UsernameAlreadyExistsException;
import com.ip.fitnessApp.model.User;
import com.ip.fitnessApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Integer id){
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new RecordNotFoundException("Ne postoji user čiji je id = " + id + "!")
                );
    }

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new UsernameAlreadyExistsException("Korisničko ime " + user.getUsername() + " je vec zauzeto!");
        else {
            User newUser = userRepository.save(user);
            return userRepository.save(user);
        }
    }

    public User updateUser(User newUser, Integer id) {
        User user = userRepository.findById(id)
                .orElse(null);
        if (user == null)
            return null;

        user = newUser;
        return userRepository.save(user);
    }

}
