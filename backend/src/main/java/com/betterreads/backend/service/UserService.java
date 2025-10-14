package com.betterreads.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.betterreads.backend.dto.UserRequestDto;
import com.betterreads.backend.dto.UserResponseDto;
import com.betterreads.backend.model.User;
import com.betterreads.backend.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public UserResponseDto registerUser(UserRequestDto userRequestDto) {
    String email = userRequestDto.getEmail();

    if (userRepository.findByEmail(email).isPresent()) {
      throw new RuntimeException("Email already in use");
    }

    String username = userRequestDto.getUsername();
    String password = userRequestDto.getPassword();

    String hashedPassword = passwordEncoder.encode(password);
    User user = new User(username, email, hashedPassword);

    userRepository.save(user);

    return mapToResponseDto(user);
  }

  public UserResponseDto loginUser(UserRequestDto userRequestDto) {
    String email = userRequestDto.getEmail();

    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Invalid Credentials"));

    String password = userRequestDto.getPassword();

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new RuntimeException("Invalid Credentials");
    }

    return mapToResponseDto(user);
  }

  public UserResponseDto mapToResponseDto(User user) {
    return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
  }
}
