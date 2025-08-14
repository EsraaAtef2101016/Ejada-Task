package com.Back.TaskBack.Apis.Controllers;


import com.Back.TaskBack.Application.Models.User;
import com.Back.TaskBack.Application.Services.UserService;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/AllUser")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }


    @PostMapping("/user")
    public ResponseEntity<?> addUser(  @RequestBody @Valid User user){
        return userService.addUser(user);
    }


    @PutMapping("/user/{id}")
    public ResponseEntity<?>  updateUser(@PathVariable Long id , @RequestBody @Valid User user){
        return  userService.updateUser(id,user);
    }


   @DeleteMapping("/user/{id}")
   public ResponseEntity<?> deleteUser( @PathVariable  Long id){
      return   userService.deleteUser(id);
   }


   @DeleteMapping("/AllUser")
    public ResponseEntity<?> deleteAllUser(){
       return   userService.deleteAllUser();
   }




    }


