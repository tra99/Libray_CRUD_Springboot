package com.lambdacode.spring.boot.crud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lambdacode.spring.boot.crud.entity.Author;
import com.lambdacode.spring.boot.crud.entity.Book;
import com.lambdacode.spring.boot.crud.repository.AuthorRepository;
import com.lambdacode.spring.boot.crud.repository.BookRepository;
import java.util.List;
import java.util.Set;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId, Book updatedBook) {
        Book existingBook = bookRepository.findById(bookId).orElse(null);

        if (existingBook != null) {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setUser(updatedBook.getUser());
            return bookRepository.save(existingBook);
        }

        return null; 
    }

    public boolean deleteBook(Long bookId) {
        Book existingBook = bookRepository.findById(bookId).orElse(null);

        if (existingBook != null) {
            bookRepository.delete(existingBook);
            return true;
        }

        return false;
    }

    public List<Book> getBooksByCatalogName(String catalogName) {
        return bookRepository.findByCatalogName(catalogName);
    }

    public List<Book> searchBooksByName(String searchTerm) {
        return bookRepository.findByTitleContaining(searchTerm);
    }

    public List<Book> getBooksByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
    public List<Author> getAuthorsByIds(Set<Long> authorIds) {
        return authorRepository.findAllById(authorIds);
    }
}

