package com.betterreads.backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betterreads.backend.dto.AuthorResponseDto;
import com.betterreads.backend.exception.AuthorNotFoundException;
import com.betterreads.backend.model.Author;
import com.betterreads.backend.repository.AuthorRepository;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorResponseDto getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()) {
            throw new AuthorNotFoundException("Author with id " + id + " doesn't exist!");
        }

        return mapToResponseDto(author.get());
    }

    public AuthorResponseDto mapToResponseDto(Author author) {
        return new AuthorResponseDto(author.getId(), author.getName());
    }
}
