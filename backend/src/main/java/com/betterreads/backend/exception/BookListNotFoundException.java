package com.betterreads.backend.exception;

public class BookListNotFoundException extends RuntimeException {
  public BookListNotFoundException(String message) {
    super(message);
  }
}
