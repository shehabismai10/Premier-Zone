package com.pl.premier_zone.auth;
//THIS CLASS FOR THE RESPONSE BODY THAT WE SEND TO THE FRONT-END AFTER THE USER LOGIN OR REGISTER

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuthenticationResponse {
    private String token;

}
