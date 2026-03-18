package com.pl.premier_zone.auth;
//THIS CLASS FOR THE SERVICE LAYER THAT WE WILL IMPLEMENT THE BUSINESS LOGIC IN IT
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pl.premier_zone.config.JwtService;
import com.pl.premier_zone.user.Role;
import com.pl.premier_zone.user.User;
import com.pl.premier_zone.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
//this required args constructor is for the final fields that we will add later like userRepository and jwtService it will generate constructor for them
public class AuthenticationService {

    //lets first make our objects that we will use in the service layer like userRepository and jwtService
    //we will use them in the register and login methods that we will create later

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder; // we will use this to encode the password before saving it to the database and to match the password when the user login
    private final AuthenticationManager authenticationManager; // we will use this to authenticate the user when he login




    public AuthenticationResponse register (RegisterRequest request){
        //this method for registering the user and saving it to the database and returning the token to the front-end

        //first we will create a new user object and set the fields from the request object that we get from the front-end
        var user= User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER) // we will set the role to USER by default when the user register
                .build();
                //then we will save the user to the database
                userRepository.save(user);

                //then we will generate the token for the user and return it to the front-end
                var jwtToken=jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();


    }


    public AuthenticationResponse login (AuthenticationRequest  request){
        
        //this will check if the email and pass is matched with database
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(), 
                request.getPassword())
        );



        //get the user from database
        var user=userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        
        

        //generate the token 
        var jwtToken=jwtService.generateToken(user);


        


        //Create an instance of the response object to serve as a container for the data
        AuthenticationResponse response = new AuthenticationResponse();
        //Wrap the generated token inside the response object to be returned as JSON
        response.setToken(jwtToken);


        return response;





        

    }



}
