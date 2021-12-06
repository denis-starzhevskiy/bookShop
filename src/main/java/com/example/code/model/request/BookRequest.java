package com.example.code.model.request;

import com.example.code.model.Languages;
import lombok.Data;

@Data
public class BookRequest {
    private Long id;
    private String bookName;
    private short price;
    private int author;
    private byte[] photoData;
    private int ISBN;
    private String description;
    private int pageAmount;
    private Languages language;
}
