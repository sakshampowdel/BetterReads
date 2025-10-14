package com.betterreads.backend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.betterreads.backend.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
  private final SecretKey secretKey;
  private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 hrs

  public JwtService(@Value("${jwt.secret}") String secret) {
    this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
  }

  public String generateToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("username", user.getUsername());
    return buildToken(claims, user.getEmail());
  }

  private String buildToken(Map<String, Object> claims, String subject) {
    long now = System.currentTimeMillis();

    return Jwts.builder()
        .claims(claims)
        .subject(subject)
        .issuedAt(new Date(now))
        .expiration(new Date(now + EXPIRATION_TIME))
        .signWith(secretKey, Jwts.SIG.HS256)
        .compact();
  }

  public Claims extractAllClaims(String token) {
    return Jwts.parser()
        .verifyWith(secretKey)
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  public String extractEmail(String token) {
    return extractAllClaims(token).getSubject();
  }

  private boolean isTokenExpired(String token) {
    return extractAllClaims(token).getExpiration().before(new Date());
  }

  public boolean isTokenValid(String token, User user) {
    String email = extractEmail(token);
    return email.equals(user.getEmail()) && !isTokenExpired(token);
  }
}
