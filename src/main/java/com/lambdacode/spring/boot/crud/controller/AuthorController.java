package com.lambdacode.spring.boot.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;

import com.lambdacode.spring.boot.crud.entity.Author;
import com.lambdacode.spring.boot.crud.service.impl.AuthorService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        if(authors.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(authors);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        if (author != null) {
            return ResponseEntity.ok(author);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAuthor(@RequestBody Map<String, String> requestBody) {
        if(requestBody.containsKey("name")) {
            Author newAuthor = new Author();
            newAuthor.setName(requestBody.get("name"));

            authorService.createAuthor(newAuthor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Author created successfully!");
        } else {
            return ResponseEntity.badRequest().body("Please provide the 'name' attribute.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        Author updatedAuthor = authorService.updateAuthor(id, author);
        if (updatedAuthor != null) {
            return ResponseEntity.ok("Author updated successfully!");
        } else {
            return ((BodyBuilder) ResponseEntity.notFound()).body("Author with ID " + id + " not found.");
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        boolean deleted = authorService.deleteAuthor(id);
        if (deleted) {
            return ResponseEntity.ok("Author with ID " + id + " deleted successfully!");
        } else {
            return ((BodyBuilder) ResponseEntity.notFound()).body("Author with ID " + id + " not found.");
        }
    }

}

