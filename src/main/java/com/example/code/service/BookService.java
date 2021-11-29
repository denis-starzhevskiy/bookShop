package com.example.code.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.example.code.controller.BookController;
import com.example.code.dto.BookDto;
import com.example.code.model.Book;
import com.example.code.model.Statistics;
import com.example.code.repository.BookRepository;
import com.example.code.repository.StatisticsRepository;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private final DtoService serviceDto;

    @Autowired
    StorageService storageService;

    public BookService(BookRepository bookRepository, DtoService serviceDto) {
        this.serviceDto = serviceDto;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public ResponseEntity<Object> getAllBooks(){
        try {
            List<BookDto> books = new ArrayList<>();
            for (Book book: bookRepository.findAll()) {
                books.add(serviceDto.wrapBookToBookDto(book));
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<Object> getBookById(Long id) {
        try{
            Book book = bookRepository.findById(id).orElseThrow(() -> new NullPointerException("Nothing to send"));
            book.setPhotoData(storageService.downloadFile(book.getPhotoName()));
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @Transactional
//    public ResponseEntity<Object> getBookBySubcategoryName(String subCategoryName) {
//        try{
//            Book book = bookRepository.findBySubCategory(subCategoryName).orElseThrow(() -> new NullPointerException("Nothing to send"));
//            System.out.println(book.getSubcategory());
//            book.setPhotoData(storageService.downloadFile(book.getPhotoName()));
//            return new ResponseEntity<>(book, HttpStatus.OK);
//        } catch(Exception ex) {
//            log.error(ex.getMessage(), ex);
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @Transactional
    public ResponseEntity<Object> createBook(Book book) {
        try{
            Statistics statistic = statisticsRepository.save(new Statistics((short) 0, 0));
            book.setId((long) statistic.getStatisticsId());
            Book savedBook = bookRepository.save(book);
            return new ResponseEntity<>(savedBook, HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<Object> updateBook(Long id, Book book) {
        try{
            book.setId(id);
            Book savedCustomer = bookRepository.save(book);
            return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteBook(Long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
