package com.betterreads.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betterreads.backend.model.Profile;
import com.betterreads.backend.model.User;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
  Optional<Profile> findByUser(User user);
}
