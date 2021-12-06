package com.example.code.dto;

import com.example.code.model.Book;
import com.example.code.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubCategoryDto {

    private int id;

    private String subCategoryName;

    private String subSlag;

    private Set<BookDto> books;

}
