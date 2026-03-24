package com.pl.premier_zone.exception;


public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(String message){

        // The 'super' keyword calls the constructor of the parent class (RuntimeException).
        // It passes the error message to the parent's storage so that 
        // later, we can retrieve it using the getMessage() method.
        //this skip using var to store the message then return it
        super(message);
    }

}
