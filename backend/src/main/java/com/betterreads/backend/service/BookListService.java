package com.betterreads.backend.service;

import org.springframework.stereotype.Service;

import com.betterreads.backend.exception.BookListNotFoundException;
import com.betterreads.backend.exception.BookNotFoundException;
import com.betterreads.backend.exception.ProfileNotFoundException;
import com.betterreads.backend.exception.UnauthorizedBookListAccessException;
import com.betterreads.backend.model.Book;
import com.betterreads.backend.model.BookList;
import com.betterreads.backend.model.Profile;
import com.betterreads.backend.model.User;
import com.betterreads.backend.repository.BookListRepository;
import com.betterreads.backend.repository.BookRepository;
import com.betterreads.backend.repository.ProfileRepository;

@Service
public class BookListService {
  private final BookListRepository bookListRepository;
  private final ProfileRepository profileRepository;
  private final BookRepository bookRepository;

  public BookListService(BookListRepository bookListRepository, ProfileRepository profileRepository,
      BookRepository bookRepository) {
    this.bookListRepository = bookListRepository;
    this.profileRepository = profileRepository;
    this.bookRepository = bookRepository;
  }

  public BookList createBookList(User user, String name) {
    Profile profile = getProfileForUser(user);
    BookList bookList = new BookList(name, profile);
    return bookListRepository.save(bookList);
  }

  public void deleteBookList(User user, Long listId) {
    BookList bookList = getOwnedBookList(user, listId);
    bookListRepository.delete(bookList);
  }

  public void addBookToList(User user, Long listId, Long bookId) {
    BookList bookList = getOwnedBookList(user, listId);
    Book book = getBook(bookId);

    bookList.addBook(book);
    bookListRepository.save(bookList);
  }

  public void removeBookFromList(User user, Long listId, Long bookId) {
    BookList bookList = getOwnedBookList(user, listId);
    Book book = getBook(bookId);

    bookList.removeBook(book);
    bookListRepository.save(bookList);
  }

  private Profile getProfileForUser(User user) {
    return profileRepository.findByUser(user)
        .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));
  }

  private BookList getOwnedBookList(User user, Long listId) {
    Profile profile = getProfileForUser(user);
    BookList bookList = bookListRepository.findById(listId)
        .orElseThrow(() -> new BookListNotFoundException("Book list not found"));

    if (!bookList.getProfile().getId().equals(profile.getId())) {
      throw new UnauthorizedBookListAccessException("You do not have permission to modify this list.");
    }
    return bookList;
  }

  private Book getBook(Long bookId) {
    return bookRepository.findById(bookId)
        .orElseThrow(() -> new BookNotFoundException("Book not found"));
  }
}
