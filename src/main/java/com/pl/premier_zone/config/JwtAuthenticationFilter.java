package com.pl.premier_zone.config;

import java.io.IOException;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@ReadingConverter
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;



    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response); // بيمرر الطلب للي بعده (زي الـ Register) بسلام
        return;
    }
        

        //the filter start here

        // 1.check if the Authorization header is present and starts with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            //if not, just pass the request to the next filter in the chain
            filterChain.doFilter(request, response);
            return;
        }

        // 2.extract token first 7 characters("Bearer")
        jwt = authHeader.substring(7);
        //extract username (email) from the token using jwtService
        userEmail = jwtService.extractUsername(jwt);

        // 3. if there is an email and the user is not yet authenticated
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            
            // 4. validate the token against the user details using jwtService
            if (jwtService.isTokenValid(jwt, userDetails)) {
                //load the user details 
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, // no credentials needed since we are using JWT (and it will be dangerous to store the password in the token) 
                        userDetails.getAuthorities() //check the authorities (roles) of the user and set them in the authentication token
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                // 5.set the authentication in the SecurityContext 
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        // 6.then pass the request to the next filter in the chain
        filterChain.doFilter(request, response);
    

}

}