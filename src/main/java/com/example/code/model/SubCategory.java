package com.example.code.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sub_category")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subCategoryId;

    @Column(name = "sub_category_name")
    private String subCategoryName;

    @Column(name = "sub_slug")
    private String subSlag;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "book_sub_category",
            joinColumns = @JoinColumn(name = "book_sub_category_id"),
            inverseJoinColumns = @JoinColumn(name = "sub_category_book_id")
    )
    private Set<Book> books;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
