package com.rescueMeal.service;

import com.rescueMeal.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secretText}")
    private String jwtSecretText;

    private SecretKey secretKey(){
        return Keys.hmacShaKeyFor(jwtSecretText.getBytes(StandardCharsets.UTF_8));
    }

public String tokenGenerate(User user){
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("email",user.getEmail())
                .claim("Authorities",user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(secretKey())
                .compact();
    }

    public String extractEmail(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims.get("email", String.class); // Extracts the email claim
    }




}
