package com.example.code.dto;

import com.example.code.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorDto {

    private int id;
    private String authorName;
    private Set<BookDto> books;

}
