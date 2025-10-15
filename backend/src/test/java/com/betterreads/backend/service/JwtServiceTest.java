package com.betterreads.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betterreads.backend.model.User;

@SpringBootTest
public class JwtServiceTest {

  @Autowired
  private JwtService jwtService;

  @Test
  void testGenerateAndValidateToken() {
    User user = new User("testuser", "test@example.com", "password");
    String token = jwtService.generateToken(user.getEmail(), user.getDisplayName());

    assertNotNull(token);
    assertTrue(jwtService.isTokenValid(token, user.getEmail()));
    assertEquals("test@example.com", jwtService.extractEmail(token));
  }
}
