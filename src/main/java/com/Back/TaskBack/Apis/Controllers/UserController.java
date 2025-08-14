package com.Back.TaskBack.Apis.Controllers;


import com.Back.TaskBack.Application.Models.User;
import com.Back.TaskBack.Application.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public List<User> getAllUser(){
        return userService. getAllUser();
    }


    @PostMapping
    public ResponseEntity<?> addUser(  @RequestBody @Valid User user){
        return userService.addUser(user);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?>  updateUser(@PathVariable Long id , @RequestBody @Valid User user){
        return  userService.updateUser(id,user);
    }


   @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteUser( @PathVariable  Long id){
      return   userService.deleteUser(id);
   }


   @DeleteMapping("/all")
    public ResponseEntity<?> deleteAllUser(){
       return   userService.deleteAllUser();
   }




    }


