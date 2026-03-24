package com.pl.premier_zone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

//import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration {

    // بننادي العسكري اللي عملناه (Filter) والمحرك (Provider)
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfiguration(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthFilter = jwtAuthFilter;
    }
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http
            .csrf(AbstractHttpConfigurer::disable) // we disable CSRF because we are using JWT and not sessions
            .authorizeHttpRequests(auth -> auth
                // 🔓 فتح أبواب الـ Swagger و OpenAPI للجميع
                .requestMatchers(
                "/v3/api-docs/**", //generate the JSON 
                    "/swagger-ui/**",          //ui for JS and CSS
                    "/swagger-ui.html"         //URL 
                    ).permitAll()
                .requestMatchers("/api/v1/auth/**").permitAll()// we allow anyone to access the authentication endpoints (like login and register) without needing to be authenticated cause they need to access them to get the token in the first place 
                .requestMatchers("/error").permitAll() // allow error path so we can see EXC
                
                .requestMatchers(HttpMethod.POST,"/api/v1/players/**" ).hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/v1/players/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/v1/players/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET,"/api/v1/players").hasAnyRole("ADMIN","USER")

                .anyRequest().authenticated() // any other request needs to be authenticated (needs a valid token)  
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // we tell Spring: "don't create sessions, rely on the Token only"
            )
            .authenticationProvider(authenticationProvider) // we connect it to the provider we created in ApplicationConfig
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // we add our filter before the default Spring Security filter that checks for username and password in the request, because we want to check for the token first and if it's valid, we will set the authentication in the security context and skip the username and password check

        return http.build(); // we build the security filter chain and return it to be used by Spring Security

        //question: do we need to abandon the default Spring Security filter that checks for username and password we cant rely on it?
        //we are using jwt auth so we don't need the default username and password authentication filter we add our own filter then the default filter will not be able to authenticate the user because we are not sending the username and password in the request body we are sending the token in the header so we need to add our filter before the default filter to check for the token first and if it's valid, we will set the authentication in the security context and skip the username and password check
        }
    //swagger token

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()

            // 1. Global Security Requirement: 
            // Tells Swagger that ALL endpoints (by default) require this security scheme.
            // "BearerAuth" is the reference name we define below in the components.
            .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))

            // 2. Components Definition:
            // This is where we describe the technical details of the security system.
            .components(new Components()
            // Define a new Security Scheme of type 'HTTP'
                .addSecuritySchemes("BearerAuth", new SecurityScheme()
                    .name("BearerAuth")             // The unique identifier for this scheme
                    .type(SecurityScheme.Type.HTTP) // We are using standard HTTP Authentication
                    .scheme("bearer")               // Crucial: Tells Swagger to prefix the token with "Bearer "
                    .bearerFormat("JWT")));         // Just a hint to the user/client that we expect a JWT
    }
    
    

    public JwtAuthenticationFilter getJwtAuthFilter() {
        return jwtAuthFilter;
    }

    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }

    
}