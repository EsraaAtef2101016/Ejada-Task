package com.Back.TaskBack.Application.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name =  "\"user\""
        , uniqueConstraints ={ @UniqueConstraint(columnNames = "email"), @UniqueConstraint(columnNames = "username")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long Id;

    @Column(unique = true)
    @Size(min = 3 , message = "Username must be at least 5 characters long")
    @Size(max = 10 ,message = "Username must be not large 10 characters long")
    @NotNull(message = "please enter username")
    private  String username ;


    @NotNull(message = "please enter password")
    @Size(min = 8 , message = "password must be at least 8 characters long")
    @Size(max = 20 ,message = "password must be not large 20 characters long")
    private String password;


    @Column(unique = true)
    @NotNull(message = "please enter email")
    @Email(message = "Your email not correct ")
    private String email;

    @NotNull
    private  String firstName;
    @NotNull
    private  String lastName;



    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
