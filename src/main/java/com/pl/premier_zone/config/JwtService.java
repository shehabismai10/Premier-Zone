package com.pl.premier_zone.config;

import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



@Service

public class JwtService {
    private static final String SECRET_KEY= "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970"; 
    //  key length is acceptable but 32 is recommended for security(hs256)

    //check username
    public String extractUsername(String token){
        return extractClaim(token , Claims::getSubject);
        //  the subject is the username/e-mail in this case
        //iat is the time the token was issued
        //exp is the time the token expires
    }


    //this method is for extracting the secure_key and using it to extract the username from the token 
    //jwt does not accept strings only accept keys
    private SecretKey getSignInKey() {
        //Decoder is used to decode the secret key from a string to bytes
        //Base64 is used to encode the secret key to a string format that can be easily stored and transmitted
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        
        //keys.hmacShaKeyFor is used to create a key object that can be used for signing and verifying JWTs using the HMAC-SHA algorithm
        return Keys.hmacShaKeyFor(keyBytes);


    }


    //this method is for extracting the claim from the token and applying the function to it
    //this is public because we will use it in the authentication provider to extract the username and check if the token is valid
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){ //
        Claims claims= Jwts.parser()
        .verifyWith(getSignInKey())//set the signing key to the key we got from the secret key (make sure its the same);
        .build()                     //build the parser
        .parseSignedClaims(token)      //decode the token and get the claims
        .getPayload();                //get the body of the claims which contains the information we need

        return claimsResolver.apply(claims); //apply the function to the claims and return the result wether its username ,exp date or iat date
    }

    private boolean isTokenExpired(String token){
        return extractClaim(token, Claims::getExpiration).before(new java.util.Date());
    }


    public boolean isTokenValid(String token, String username){
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    


    public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    // check if the username from the token matches the username from the user details and also check if the token is not expired      
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
}

    public String generateToken(UserDetails userDetails) {
            java.util.Map<String, Object> extraClaims = new java.util.HashMap<>();
            extraClaims.put("roles", userDetails.getAuthorities().stream()
            .map(org.springframework.security.core.GrantedAuthority::getAuthority)
            .collect(java.util.stream.Collectors.toList()));

    return Jwts.builder()
            .subject(userDetails.getUsername()) // set the subject of the token to the username (or email) of the user
            .claims(extraClaims) // 👈 دي الحتة اللي كانت ناقصة (بتحط الـ Roles جوه الـ Payload)            .issuedAt(new java.util.Date(System.currentTimeMillis())) //when the token is issued 
            .expiration(new java.util.Date(System.currentTimeMillis() + 1000 * 60 *60* 24)) // set the exp time for 24 hours (1000 milliseconds * 60 seconds * 60 minutes * 24 hours)    
            .signWith(getSignInKey()) // sign the token with the secret key
            .compact();               // build the token and serialize it to a string
}



}
