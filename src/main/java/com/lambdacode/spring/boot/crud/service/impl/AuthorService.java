package com.lambdacode.spring.boot.crud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lambdacode.spring.boot.crud.entity.Author;
import com.lambdacode.spring.boot.crud.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long authorId, Author updatedAuthor) {
        Author existingAuthor = authorRepository.findById(authorId).orElse(null);

        if (existingAuthor != null) {
            existingAuthor.setName(updatedAuthor.getName());
            // Update other fields if needed
            return authorRepository.save(existingAuthor);
        }

        return null; // Handle if author is not found
    }

    public boolean deleteAuthor(Long authorId) {
        Author existingAuthor = authorRepository.findById(authorId).orElse(null);

        if (existingAuthor != null) {
            authorRepository.delete(existingAuthor);
            return true; // Deletion successful
        }

        return false; // Handle if author is not found or deletion fails
    }
}

