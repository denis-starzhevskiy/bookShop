package com.example.code.service;

import com.example.code.controller.BookController;
import com.example.code.dto.BookDto;
import com.example.code.dto.CategoryDto;
import com.example.code.model.Book;
import com.example.code.model.Category;
import com.example.code.repository.BookRepository;
import com.example.code.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DtoService dtoService;

    @Transactional
    public ResponseEntity<Object> getAllCategories(){
        try {
            List<CategoryDto> categories = new ArrayList<>();

            for (Category category: categoryRepository.findAll()) {
                categories.add(dtoService.wrapCategory(category));
            }
            return new ResponseEntity<Object>(categories, HttpStatus.OK);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
}
