package com.example.code.controller;

import com.example.code.dto.AuthorDto;
import com.example.code.model.Author;
import com.example.code.model.request.AuthorRequest;
import com.example.code.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<Object> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/{authorName}")
    public ResponseEntity<Object> getAuthor(@PathVariable String authorName){
        return authorService.getAuthor(authorName);
    }

    @PutMapping
    public ResponseEntity<Object> updateAuthor(@RequestBody AuthorRequest authorRequest){
        return authorService.updateAuthor(authorRequest);
    }
}
