package com.example.code.dto;

import com.example.code.model.Book;
import com.example.code.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubCategoryDto {

    private int subCategoryId;

    private String subCategoryName;

    private String subSlag;

    private Set<BookDto> books;

    public SubCategoryDto() {
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getSubSlag() {
        return subSlag;
    }

    public void setSubSlag(String subSlag) {
        this.subSlag = subSlag;
    }

    public Set<BookDto> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDto> books) {
        this.books = books;
    }
}
