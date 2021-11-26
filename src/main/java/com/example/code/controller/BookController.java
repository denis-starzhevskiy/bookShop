package com.example.code.controller;

import com.example.code.model.Book;
import com.example.code.model.Permission;
import com.example.code.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    //@PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Object> getAllBooks(){
        return service.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable("id") Long id) {
        return service.getBookById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createBook(@RequestBody Book book) {
        return service.createBook(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        return service.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {
        return service.deleteBook(id);
    }


}
