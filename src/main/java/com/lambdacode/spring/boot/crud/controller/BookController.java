package com.lambdacode.spring.boot.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;

import com.lambdacode.spring.boot.crud.dto.AuthorWithBooksDTO;
import com.lambdacode.spring.boot.crud.entity.Book;
import com.lambdacode.spring.boot.crud.service.impl.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        if (createdBook != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Book created successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create book");
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        if (updatedBook != null) {
            return ResponseEntity.ok("Book updated successfully!");
        } else {
            return ((BodyBuilder) ResponseEntity.notFound()).body("Book not found or update failed.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        boolean deleted = bookService.deleteBook(id);
        if (deleted) {
            return ResponseEntity.ok("Book with ID " + id + " deleted successfully!");
        } else {
            return ((BodyBuilder) ResponseEntity.notFound()).body("Book with ID " + id + " not found or deletion failed.");
        }
    }  
    // search book by title
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooksByName(@RequestParam("title") String title) {
        List<Book> foundBooks = bookService.searchBooksByName(title);
        if (!foundBooks.isEmpty()) {
            return ResponseEntity.ok(foundBooks);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // filter book base on catalog=user
    @GetMapping("/filter-by-catalog")
    public ResponseEntity<List<Book>> filterBooksByCatalog(@RequestParam("catalogName") String catalogName) {
        List<Book> filteredBooks = bookService.getBooksByCatalogName(catalogName);
        if (!filteredBooks.isEmpty()) {
            return ResponseEntity.ok(filteredBooks);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filter-by-author")
    public ResponseEntity<List<Book>> filterBooksByAuthor(@RequestParam("authorId") Long authorId) {
        List<Book> filteredBooks = bookService.getBooksByAuthorId(authorId);
        if (!filteredBooks.isEmpty()) {
            return ResponseEntity.ok(filteredBooks);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

