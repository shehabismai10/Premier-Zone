package com.pl.premier_zone.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//THIS CLASS FOR THE REQUEST BODY THAT COMING FROM THE FRONT-END WHEN THE USER WANT TO REGISTER

@Data
//data annotation from lombok library to generate getters and setters for all fields, toString, equals and hashCode methods, and a constructor for all final fields
@Builder
// for example 
@AllArgsConstructor
//all args constructor make u able to create an object to the class and set all the fields at once, without the need to call the setters for each field
//new RegisterRequest("ahmed", "a@a.com", "123")
@NoArgsConstructor
//make json that coming from the front-end to be converted to an object we can work with it, without the need to create an object and set the fields one by one
public class RegisterRequest {
    private String username;
    private String email;
    private String password;

}
