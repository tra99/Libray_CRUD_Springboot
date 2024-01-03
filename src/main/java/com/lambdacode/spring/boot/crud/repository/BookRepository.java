package com.lambdacode.spring.boot.crud.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lambdacode.spring.boot.crud.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String searchTerm);

    @Query("SELECT b FROM Book b WHERE b.user.name = :name")
    List<Book> findByCatalogName(@Param("name") String catalogName);

    List<Book> findByAuthorId(Long authorId);
}

