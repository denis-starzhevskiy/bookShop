package com.example.code.service;

import com.example.code.controller.BookController;
import com.example.code.dto.AuthorDto;
import com.example.code.dto.BookDto;
import com.example.code.dto.CategoryDto;
import com.example.code.dto.SubCategoryDto;
import com.example.code.model.Author;
import com.example.code.model.Book;
import com.example.code.model.Category;
import com.example.code.model.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class DtoService {

    @Autowired
    private StorageService storageService;

    public BookDto wrapBookToBookDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setBookName(book.getBookName());
//        bookDto.setDescription(book.getDescription());
//        bookDto.setPageAmount(book.getPageAmount());
        bookDto.setPrice(book.getPrice());
//        bookDto.setISBN(book.getISBN());
        bookDto.setPhotoData(storageService.downloadFile(book.getPhotoName()));
        bookDto.setAuthor(book.getAuthor());
//        bookDto.setCategory(book.getCategory());
//        bookDto.setStatistics(book.getStatistics());
//        bookDto.setSubCategorySet(book.getSubcategory());
        bookDto.add(linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel());
        return bookDto;
    }

    public CategoryDto wrapCategory(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setBooks(category.getBooks().stream()
                .map(this::wrapBookToBookDto)
                .collect(Collectors.toSet()));
        categoryDto.setSubCategorySet(category.getSubCategories());
        return categoryDto;
    }

    public AuthorDto wrapAuthor(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setAuthor_id(author.getAuthor_id());
        authorDto.setAuthorName(author.getAuthorName());
        authorDto.setBooks(author.getBooks().stream()
                .map(this::wrapBookToBookDto)
                .collect(Collectors.toSet()));
        return authorDto;
    }

    public SubCategoryDto wrapSubCategory(SubCategory subCategory){
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        subCategoryDto.setSubCategoryId(subCategory.getSubCategoryId());
        subCategoryDto.setSubCategoryName(subCategory.getSubCategoryName());
        subCategoryDto.setCategory(subCategory.getCategory());
        subCategoryDto.setSubSlag(subCategory.getSubSlag());
        subCategoryDto.setBooks(subCategory.getBooks());
        return subCategoryDto;
    }
}
