package com.betterreads.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betterreads.backend.dto.UserRequestDto;
import com.betterreads.backend.dto.UserResponseDto;
import com.betterreads.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;

  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponseDto> registerUser(
      @Valid @RequestBody UserRequestDto userRequestDto) {
    UserResponseDto userResponseDto = userService.registerUser(userRequestDto);
    return ResponseEntity.status(201).body(userResponseDto);
  }

  @PostMapping("/login")
  public ResponseEntity<UserResponseDto> loginUser(
      @Valid @RequestBody UserRequestDto userRequestDto) {
    UserResponseDto userResponseDto = userService.loginUser(userRequestDto);
    return ResponseEntity.ok(userResponseDto);
  }

}
