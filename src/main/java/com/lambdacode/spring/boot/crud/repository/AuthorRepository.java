package com.lambdacode.spring.boot.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lambdacode.spring.boot.crud.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
