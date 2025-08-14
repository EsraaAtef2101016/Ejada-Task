package com.Back.TaskBack.Application.Services;

import com.Back.TaskBack.Application.Models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService  {
    public ResponseEntity<?> getUserById(Long id);
    public ResponseEntity<?> addUser(User user);
    public List<User> getAllUser();
    public ResponseEntity<?> deleteUser(Long id);
    public ResponseEntity<?> deleteAllUser();
    public ResponseEntity<?> updateUser(Long id, User user);
}
