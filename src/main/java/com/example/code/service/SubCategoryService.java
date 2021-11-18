package com.example.code.service;

import com.example.code.dto.SubCategoryDto;
import com.example.code.model.SubCategory;
import com.example.code.repository.SubCategoryRepository;
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
public class SubCategoryService {

    Logger logger = LoggerFactory.getLogger(SubCategoryService.class);

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private DtoService dtoService;

    @Transactional
    public ResponseEntity<Object> getAllSubCategories(){
        try{
            List<SubCategoryDto> subCategories = new ArrayList<>();
            for(SubCategory subCategory: subCategoryRepository.findAll()){
                subCategories.add(dtoService.wrapSubCategory(subCategory));
            }
            return new ResponseEntity<>(subCategories, HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
