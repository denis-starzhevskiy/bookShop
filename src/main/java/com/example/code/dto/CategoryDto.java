package com.example.code.dto;

import com.example.code.model.SubCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDto extends RepresentationModel<CategoryDto> {

    private int categoryId;

    private String categoryName;

    private Set<BookDto> books;

    private Set<SubCategory> subCategorySet;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<BookDto> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDto> books) {
        this.books = books;
    }

    public Set<SubCategory> getSubCategoryDtoSet() {
        return subCategorySet;
    }

    public void setSubCategorySet(Set<SubCategory> subCategorySet) {
        this.subCategorySet = subCategorySet;
    }
}
