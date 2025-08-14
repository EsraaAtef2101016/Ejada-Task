package com.Back.TaskBack.Application.Services.implementation;

import com.Back.TaskBack.Application.Models.User;
import com.Back.TaskBack.Application.Repositories.UserRepo;
import com.Back.TaskBack.Application.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImp  implements UserService {

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
        User existing = userRepo.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", 404,
                    "error", "Not Found",
                    "message", "User with ID " + id + " not found."

            ));
        }
        else {
            try {
                User existuser = userRepo.findById(id).orElse(null);
                existuser.setEmail(user.getEmail());
                existuser.setFirstName(user.getFirstName());
                existuser.setPassword(user.getPassword());
                existuser.setLastName(user.getLastName());
                existuser.setUsername(user.getUsername());

                userRepo.save(existuser);
                return ResponseEntity.ok(Map.of(
                        "userId", id,
                        "username", user.getUsername(),
                        "email", user.getEmail(),
                        "firstName", user.getFirstName(),
                        "lastName", user.getLastName()
                ));

            } catch (org.springframework.dao.DataIntegrityViolationException ex) {
                // Fallback (in case of a race condition)
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("status", 409, "error", "Conflict",
                                "message", "Email or username already exists"));
            }

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
