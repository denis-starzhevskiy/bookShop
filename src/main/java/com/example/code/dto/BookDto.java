package com.example.code.dto;

import com.example.code.model.Author;
import com.example.code.model.Category;
import com.example.code.model.Statistics;
import com.example.code.model.SubCategory;
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
//    private String description;
//    private int pageAmount;
//    private int ISBN;
//    private Category category;
//    private Statistics statistics;
//    private Set<SubCategory> subCategorySet;

}
