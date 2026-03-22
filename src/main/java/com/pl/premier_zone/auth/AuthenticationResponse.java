package com.pl.premier_zone.auth;
//THIS CLASS FOR THE RESPONSE BODY THAT WE SEND TO THE FRONT-END AFTER THE USER LOGIN OR REGISTER




public class AuthenticationResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
