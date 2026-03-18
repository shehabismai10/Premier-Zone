    package com.pl.premier_zone.auth;

    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import lombok.RequiredArgsConstructor;

    @RestController
    @RequestMapping("/api/v1/auth")
    @RequiredArgsConstructor
    public class AuthenticationController {


        private final AuthenticationService service;

        @PostMapping("/register")
        public ResponseEntity<AuthenticationResponse>register(
            @RequestBody  RegisterRequest request
            //convert json request body to java object
        ){
            return ResponseEntity.ok(service.register(request));
            //send the account username and password to the register method in service and generate jwt
        }

        @PostMapping("/login")
        public ResponseEntity<AuthenticationResponse>login(
            @RequestBody AuthenticationRequest request
            //extract user info from the json body request

        ){
            return ResponseEntity.ok(service.login(request));
            //if the info right send it to login func in service to get the jwt
        }



    }
