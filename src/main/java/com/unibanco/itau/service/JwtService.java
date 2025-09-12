package com.unibanco.itau.service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    public Key secrectKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+60000L))
                .signWith(secrectKey)
                .compact();
    }

    public JwtParser parseToken(){
        return Jwts.parserBuilder()
                .setSigningKey(secrectKey)
                .build();
    }
    public boolean isValidToken(String token, UserDetails userDetails) {
        var parsedToken = parseToken().parseClaimsJws(token);
        return parsedToken.getBody().getSubject().equals(userDetails.getUsername());
    }

    public String extractUsername(String token){
        return parseToken()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
