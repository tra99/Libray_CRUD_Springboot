package com.lambdacode.spring.boot.crud.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BookId;

    private String title;

    public Long getBookId() {
        return BookId;
    }

    public void setBookId(Long BookId) {
        this.BookId = BookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "author_id") 
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    @ManyToMany
    @JoinTable(
        name = "Book_Author",
        joinColumns = @JoinColumn(name = "BookId"),
        inverseJoinColumns = @JoinColumn(name = "Id")
    )
    private Set<Author> authors;
}

