package com.example.code.dto;

import com.example.code.model.Author;
import com.example.code.model.Category;
import com.example.code.model.Statistics;
import com.example.code.model.SubCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.util.Set;

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
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public int getPageAmount() {
//        return pageAmount;
//    }
//
//    public void setPageAmount(int pageAmount) {
//        this.pageAmount = pageAmount;
//    }
//
//    public short getPrice() {
//        return price;
//    }
//
//    public void setPrice(short price) {
//        this.price = price;
//    }
//
//    public int getISBN() {
//        return ISBN;
//    }
//
//    public void setISBN(int ISBN) {
//        this.ISBN = ISBN;
//    }
//
//    public byte[] getPhotoData() {
//        return photoData;
//    }
//
//    public void setPhotoData(byte[] photoData) {
//        this.photoData = photoData;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public Author getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(Author author) {
//        this.author = author;
//    }
//
//    public Statistics getStatistics() {
//        return statistics;
//    }
//
//    public void setStatistics(Statistics statistics) {
//        this.statistics = statistics;
//    }
//
//    public Set<SubCategory> getSubCategorySet() {
//        return subCategorySet;
//    }
//
//    public void setSubCategorySet(Set<SubCategory> subCategorySet) {
//        this.subCategorySet = subCategorySet;
//    }
}
