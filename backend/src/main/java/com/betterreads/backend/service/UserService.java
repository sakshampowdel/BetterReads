package com.betterreads.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.betterreads.backend.dto.LoginRequestDto;
import com.betterreads.backend.dto.UserRequestDto;
import com.betterreads.backend.dto.UserResponseDto;
import com.betterreads.backend.exception.InvalidCredentialsException;
import com.betterreads.backend.exception.UserAlreadyExistsException;
import com.betterreads.backend.exception.UserNotFoundException;
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
      throw new UserAlreadyExistsException("Email already in use!");
    }

    String username = userRequestDto.getDisplayName();
    String password = userRequestDto.getPassword();

    String hashedPassword = passwordEncoder.encode(password);
    User user = new User(username, email, hashedPassword);

    userRepository.save(user);

    return mapToResponseDto(user);
  }

  public UserResponseDto loginUser(LoginRequestDto loginRequestDto) {
    String email = loginRequestDto.getEmail();

    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException("Invalid Credentials!"));

    String password = loginRequestDto.getPassword();

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new InvalidCredentialsException("Invalid Credentials!");
    }

    return mapToResponseDto(user);
  }

  public UserResponseDto mapToResponseDto(User user) {
    return new UserResponseDto(user.getId(), user.getDisplayName(), user.getEmail());
  }
}
