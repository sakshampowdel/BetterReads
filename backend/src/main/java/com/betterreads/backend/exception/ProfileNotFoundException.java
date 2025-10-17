package com.betterreads.backend.exception;

public class ProfileNotFoundException extends RuntimeException {
  public ProfileNotFoundException(String message) {
    super(message);
  }
}
