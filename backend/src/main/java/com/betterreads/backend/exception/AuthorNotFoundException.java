package com.betterreads.backend.exception;

public class AuthorNotFoundException extends RuntimeException {
  public AuthorNotFoundException(String message) {
    super(message);
  }
}
