package com.my.personal.MyProject;


import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
//@Component
//public class JwtUtil {
//
//    @Value("${jwt.secret.key}")
//    private String secretKey; // Non-static
//     static SecretKey key; // Non-static
//    private static final String PRE_FIX = "Bearer";
//    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds
//
//    @PostConstruct
//    public void init() {
//        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
//    }
//
//    public  String generateToken(Authentication auth) {
//        return Jwts.builder()
//                .setIssuer("UsApplication")
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .claim("email", auth.getName())
//                .signWith(key)
//                .compact();
//    }
//
//    public static String getEmailFromJwtToken(String jwt) {
//        if (jwt.startsWith(PRE_FIX)) {
//            jwt = jwt.substring(PRE_FIX.length());
//        }
//
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(jwt)
//                .getBody();
//
//        return String.valueOf(claims.get("email"));
//    }
//    


@Component
public class JwtUtil {
	
	//Rajib

    @Value("${jwt.secret.key}")
    private String secretKey; // From application.properties

    private static SecretKey key; // Shared key
    private static final String PRE_FIX = "Bearer";
    private static final long EXPIRATION_TIME = 86400000; // 1 day

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // 1. Generate Token
    public String generateToken(Authentication auth) {
        return Jwts.builder()
                .setIssuer("UsApplication")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("email", auth.getName()) // auth.getName() = email
                .signWith(key)
                .compact();
    }

    // 2. Extract email from JWT token
    public String extractEmail(String jwt) {
        return getEmailFromJwtToken(jwt);
    }

    // 3. Validate token
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String email = extractEmail(token);
            return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;  
        }
    }

    // Utility: Get email from claims
    public static String getEmailFromJwtToken(String jwt) {
        if (jwt.startsWith(PRE_FIX)) {
            jwt = jwt.substring(PRE_FIX.length());
        }

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return String.valueOf(claims.get("email"));
    }

    // Utility: Check token expiration
    private boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }
}



