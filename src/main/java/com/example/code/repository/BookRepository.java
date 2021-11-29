package com.example.code.repository;

import com.example.code.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b left join fetch b.category category where b.id = :id")
    Book findBookById(@Param("id") Long id);
}