package com.Back.TaskBack.Application.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public @Size(min = 3, message = "Username must be at least 5 characters long") @Size(max = 10, message = "Username must be not large 10 characters long") @NotNull(message = "please enter username") String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 3, message = "Username must be at least 5 characters long") @Size(max = 10, message = "Username must be not large 10 characters long") @NotNull(message = "please enter username") String username) {
        this.username = username;
    }

    public @NotNull(message = "please enter password") @Size(min = 8, message = "password must be at least 8 characters long") @Size(max = 20, message = "password must be not large 20 characters long") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "please enter password") @Size(min = 8, message = "password must be at least 8 characters long") @Size(max = 20, message = "password must be not large 20 characters long") String password) {
        this.password = password;
    }

    public @NotNull(message = "please enter email") @Email(message = "Your email not correct ") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "please enter email") @Email(message = "Your email not correct ") String email) {
        this.email = email;
    }

    public @NotNull String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public @NotNull String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull String lastName) {
        this.lastName = lastName;
    }
}
