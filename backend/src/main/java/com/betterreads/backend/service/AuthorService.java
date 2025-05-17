package com.betterreads.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.betterreads.backend.dto.AuthorRequestDto;
import com.betterreads.backend.dto.AuthorResponseDto;
import com.betterreads.backend.dto.BookRequestDto;
import com.betterreads.backend.dto.PaginatedResponseDto;
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

    public PaginatedResponseDto<AuthorResponseDto> getAllAuthors(Pageable pageable) {
        Page<Author> page = authorRepository.findAll(pageable);
        List<Author> authors = page.getContent();
        List<AuthorResponseDto> authorResponseDtos = new ArrayList<>();

        for (Author author: authors) {
            authorResponseDtos.add(mapToResponseDto(author));
        }

        PaginatedResponseDto<AuthorResponseDto> paginatedResponseDto = new PaginatedResponseDto<>(authorResponseDtos, page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());

        return paginatedResponseDto;
    }

    public AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto) {
        String name = authorRequestDto.getName();
        String openLibraryId = authorRequestDto.getOpenLibraryId();

        Author author = new Author(name, openLibraryId);
        authorRepository.save(author);

        return mapToResponseDto(author);
    }

    public AuthorResponseDto updateAuthorById(Long id, BookRequestDto bookRequestDto) {
        return null;
    }

    public void deleteAuthorById(Long id) {
    }

    public AuthorResponseDto mapToResponseDto(Author author) {
        return new AuthorResponseDto(author.getId(), author.getName());
    }
}
