package com.example.code.controller;

import com.example.code.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Object> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<Object> getCategory(@PathVariable String categoryName){
        return categoryService.getCategory(categoryName);
    }
}
