package com.example.code.service;

import com.example.code.dto.AuthorDto;
import com.example.code.model.Author;
import com.example.code.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    private Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private DtoService dtoService;

    @Transactional
    public ResponseEntity<Object> getAllAuthors(){
        try {
            List<AuthorDto> authors = new ArrayList<>();

            for (Author author: authorRepository.findAll()) {
                authors.add(dtoService.wrapAuthor(author));
            }
            return new ResponseEntity<Object>(authors, HttpStatus.OK);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

}
