package com.example.code.service;

import com.example.code.controller.BookController;
import com.example.code.dto.BookDto;
import com.example.code.model.Book;
import com.example.code.model.Statistics;
import com.example.code.repository.BookRepository;
import com.example.code.repository.StatisticsRepository;
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
public class BookService {

    private Logger logger;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private final DtoService serviceDto;

    public BookService(BookRepository bookRepository, DtoService serviceDto) {
        logger = LoggerFactory.getLogger(BookController.class);
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
            return new ResponseEntity<Object>(books, HttpStatus.OK);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<Object> getBookById(Long id) {
        try{
            BookDto book = serviceDto.wrapBookToBookDto(bookRepository.findById(id).get());
            if(book != null) {
                return new ResponseEntity<Object>(book, HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<Object> createBook(Book book) {
        try{
            Statistics statistic = statisticsRepository.save(new Statistics((short) 0, 0));
            book.setId((long) statistic.getStatisticsId());
            Book savedBook = bookRepository.save(book);
            return new ResponseEntity<Object>(savedBook, HttpStatus.OK);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<Object> updateBook(Long id, Book book) {
        try{
            book.setId(id);
            Book savedCustomer = bookRepository.save(book);
            return new ResponseEntity<Object>(savedCustomer, HttpStatus.OK);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteBook(Long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }

}
