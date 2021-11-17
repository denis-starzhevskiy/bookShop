package com.example.code.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CategoryDto extends RepresentationModel<CategoryDto> {

    private int categoryId;

    private String categoryName;

    private Set<BookDto> books;

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
}
