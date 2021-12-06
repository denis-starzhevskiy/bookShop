package com.example.code.model.request;

import lombok.Data;

@Data
public class AuthorRequest {

    private int id;
    private String authorName;
    private String books;

}
