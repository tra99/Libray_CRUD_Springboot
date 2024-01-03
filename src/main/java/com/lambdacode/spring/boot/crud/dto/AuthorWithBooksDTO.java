package com.lambdacode.spring.boot.crud.dto;

import java.util.List;

import com.lambdacode.spring.boot.crud.entity.Author;
import com.lambdacode.spring.boot.crud.entity.Book;

public class AuthorWithBooksDTO {
    private Author author;
    private List<Book> books;
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public AuthorWithBooksDTO(Author author, List<Book> books) {
        this.author = author;
        this.books = books;
    }
}
