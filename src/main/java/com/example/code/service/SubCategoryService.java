package com.example.code.service;

import com.example.code.dto.SubCategoryDto;
import com.example.code.model.SubCategory;
import com.example.code.repository.SubCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SubCategoryService {

    Logger logger = LoggerFactory.getLogger(SubCategoryService.class);

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private DtoService dtoService;

    @Transactional
    public ResponseEntity<Object> getAllSubCategories(){
        try{
            List<SubCategory> subCategories = subCategoryRepository.findAll();
            return new ResponseEntity<>(subCategories, HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<Object> getSubCategory(String subCategoryName) {
        try{
            SubCategory subCategory = subCategoryRepository.findSubCategoryBySubCategoryName(subCategoryName);
            SubCategoryDto subCategoryDto = dtoService.wrapSubCategory(subCategory);
            return new ResponseEntity<>(subCategoryDto, HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<Object> updateSubCategory(SubCategoryDto subCategoryDto) {
        try{
            SubCategory subCategory = subCategoryRepository.findById(subCategoryDto.getId()).orElseThrow(() -> new NullPointerException("NULL"));
            subCategory.setSubCategoryName(subCategoryDto.getSubCategoryName());
            subCategory.setSubSlag(subCategoryDto.getSubSlag());
            SubCategory subCategorySave = subCategoryRepository.save(subCategory);
            return new ResponseEntity<>(subCategorySave, HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteBook(int id) {
        try {
            subCategoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
