package com.lambdacode.spring.boot.crud.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lambdacode.spring.boot.crud.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

