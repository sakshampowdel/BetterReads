package com.betterreads.backend.controller;

import com.betterreads.backend.model.Book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    @GetMapping
    public Book getBook(@PathVariable Long id) {
        return new Book(null, null, null);
    }
}
