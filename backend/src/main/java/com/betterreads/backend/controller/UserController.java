package com.betterreads.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betterreads.backend.dto.JwtResponseDto;
import com.betterreads.backend.dto.LoginRequestDto;
import com.betterreads.backend.dto.UserRequestDto;
import com.betterreads.backend.dto.UserResponseDto;
import com.betterreads.backend.model.User;
import com.betterreads.backend.service.JwtService;
import com.betterreads.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;
  private final JwtService jwtService;

  public UserController(UserService userService, JwtService jwtService) {
    this.userService = userService;
    this.jwtService = jwtService;
  }

  @PostMapping("/register")
  public ResponseEntity<JwtResponseDto> registerUser(
      @Valid @RequestBody UserRequestDto userRequestDto) {
    UserResponseDto userResponseDto = userService.registerUser(userRequestDto);
    String token = jwtService.generateToken(userResponseDto.getEmail(), userResponseDto.getDisplayName());
    return ResponseEntity.status(201).body(new JwtResponseDto(token, userResponseDto));
  }

  @PostMapping("/login")
  public ResponseEntity<JwtResponseDto> loginUser(
      @Valid @RequestBody LoginRequestDto loginRequestDto) {
    UserResponseDto userResponseDto = userService.loginUser(loginRequestDto);
    String token = jwtService.generateToken(userResponseDto.getEmail(), userResponseDto.getDisplayName());
    return ResponseEntity.ok(new JwtResponseDto(token, userResponseDto));
  }

  @GetMapping("/me")
  public ResponseEntity<UserResponseDto> getCurrentUser(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    UserResponseDto userResponseDto = new UserResponseDto(user.getId(), user.getDisplayName(), user.getEmail());
    return ResponseEntity.ok(userResponseDto);
  }
}
