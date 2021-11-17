package com.example.code.dto;

import com.example.code.model.Book;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
public class AuthorDto {

    private int author_id;
    private String authorName;
    private Set<BookDto> books;

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Set<BookDto> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDto> books) {
        this.books = books;
    }
}
