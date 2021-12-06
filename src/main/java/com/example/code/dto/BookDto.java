package com.example.code.dto;

import com.example.code.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BookDto extends RepresentationModel<BookDto> {

    private Long id;
    private String bookName;
    private short price;
    private Author author;
    private byte[] photoData;
    private int ISBN;
    private String description;
    private int pageAmount;
    private Languages language;
}
