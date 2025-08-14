package com.Back.TaskBack.Application.Services;

import com.Back.TaskBack.Application.Models.User;
import com.Back.TaskBack.Application.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public ResponseEntity<?> getUserById(Long id) {
        Optional<User> user = userRepo.findById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "status", 404,
                            "error", "Not Found",
                            "message", "User with ID " + id + " not found.")
                    );
        }
    }

    public ResponseEntity<?> addUser(User user) {
        try {
            userRepo.save(user);
            return ResponseEntity.ok(Map.of(
                    "userId", user.getId(),
                    "username", user.getUsername(),
                    "email", user.getEmail(),
                    "firstName", user.getFirstName(),
                    "lastName", user.getLastName()
            ));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "status", 409,
                    "error", "Conflict",
                    "message", "Username or email already exists."

            ));
        }


    }


    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    public ResponseEntity<?> updateUser(Long id, User user) {

        if (userRepo.findById(id).isPresent()) {
            User existuser = userRepo.findById(id).orElse(null);
                existuser.setEmail(user.getEmail());
                existuser.setFirstName(user.getFirstName());
                existuser.setPassword(user.getPassword());
                existuser.setLastName(user.getLastName());
                existuser.setUsername(user.getUsername());

                userRepo.save(existuser);
                return ResponseEntity.ok(Map.of(
                        "userId", user.getId(),
                        "username", user.getUsername(),
                        "email", user.getEmail(),
                        "firstName", user.getFirstName(),
                        "lastName", user.getLastName()
                ));

        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", 404,
                    "error", "Not Found",
                    "message", "User with ID " + id + " not found."

            ));
        }

    }

    public ResponseEntity<?> deleteUser(Long id) {
        if(userRepo.findById(id).isPresent()) {
            userRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of("Status",200,
                            "message","User Delete successfully.")
            );
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Map.of("status",404,
                            "error","Not Found",
                            "message", "User with ID "+id+" not found." )
            );

        }
    }


    public ResponseEntity<?> deleteAllUser() {
        userRepo.deleteAll();

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of("Status", 200,
                        "message", "User Delete successfully.")
        );

    }


}
