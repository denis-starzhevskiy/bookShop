package com.example.code.dto;

import com.example.code.model.SubCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDto extends RepresentationModel<CategoryDto> {

    private int id;

    private String categoryName;

    private Set<BookDto> books;

    private Set<SubCategory> subCategorySet;
}
