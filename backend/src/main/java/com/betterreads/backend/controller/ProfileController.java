package com.betterreads.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betterreads.backend.dto.ProfileResponseDto;
import com.betterreads.backend.model.User;
import com.betterreads.backend.service.ProfileService;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
  private final ProfileService profileService;

  public ProfileController(ProfileService profileService) {
    this.profileService = profileService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProfileResponseDto> getProfileById(@PathVariable Long id) {
    ProfileResponseDto profileResponseDto = profileService.getProfileById(id);
    return ResponseEntity.ok(profileResponseDto);
  }

  @GetMapping("/me")
  public ResponseEntity<ProfileResponseDto> getCurrentUserProfile(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    ProfileResponseDto profileResponseDto = profileService.getProfileByUser(user);
    return ResponseEntity.ok(profileResponseDto);
  }
}
