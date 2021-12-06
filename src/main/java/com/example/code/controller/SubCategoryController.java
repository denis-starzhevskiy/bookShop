package com.example.code.controller;

import com.example.code.dto.SubCategoryDto;
import com.example.code.model.SubCategory;
import com.example.code.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/subCategories")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping
    public ResponseEntity<Object> getAllSubcategories(){
        return subCategoryService.getAllSubCategories();
    }

    @GetMapping("/{subCategoryName}")
    public ResponseEntity<Object> getSubCategory(@PathVariable String subCategoryName){
        return subCategoryService.getSubCategory(subCategoryName);
    }

    @PutMapping
    public ResponseEntity<Object> updateSubCategory(@RequestBody SubCategoryDto subCategoryDto){
        return subCategoryService.updateSubCategory(subCategoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") int id) {
        return subCategoryService.deleteBook(id);
    }

}
