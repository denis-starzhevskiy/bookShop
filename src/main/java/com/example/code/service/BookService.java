package com.example.code.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.example.code.controller.BookController;
import com.example.code.dto.BookDto;
import com.example.code.model.Book;
import com.example.code.model.Languages;
import com.example.code.model.Statistics;
import com.example.code.model.request.BookRequest;
import com.example.code.repository.AuthorRepository;
import com.example.code.repository.BookRepository;
import com.example.code.repository.StatisticsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    private final StatisticsRepository statisticsRepository;

    private final DtoService serviceDto;

    private final AuthorRepository authorRepository;

    private final StorageService storageService;

    public BookService(BookRepository bookRepository, DtoService serviceDto, StatisticsRepository statisticsRepository, AuthorRepository authorRepository, StorageService storageService) {
        this.bookRepository = bookRepository;
        this.statisticsRepository = statisticsRepository;
        this.serviceDto = serviceDto;
        this.authorRepository = authorRepository;
        this.storageService = storageService;
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
            Statistics statistics = statisticsRepository.findById(book.getStatistics().getId()).orElseThrow(() -> new NotFoundException("There isn't statistics for this book"));
            statistics.setViews(statistics.getViews() + 1);
            statisticsRepository.save(statistics);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<Object> createBook(Book book) {
        try{
            Statistics statistic = statisticsRepository.save(new Statistics(new Timestamp(System.currentTimeMillis()), (short) 0, 0));
            book.setId((long) statistic.getId());
            Book savedBook = bookRepository.save(book);
            return new ResponseEntity<>(savedBook, HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<Object> updateBook(BookRequest bookRequest) {
        try{
            Book book = getBookFromRequest(bookRequest);
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

    public Book getBookFromRequest(BookRequest bookRequest) throws IOException {
        System.out.println(Arrays.toString(bookRequest.getPhotoData()));
        Book book = bookRepository.findById(bookRequest.getId()).orElseThrow(() -> new NotFoundException("Book wasn't founded"));
        book.setBookName(bookRequest.getBookName());
        book.setAuthor(authorRepository.findById(bookRequest.getAuthor()).orElseThrow(() -> new NotFoundException("Author wasn't founded")));
        if(bookRequest.getPhotoData() != null) {
            InputStream inputStream = new ByteArrayInputStream(bookRequest.getPhotoData());
            File file = new File("photoName.png");
            file.createNewFile();
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            book.setPhotoName(storageService.uploadPhoto(file));
            book.setPhotoData(bookRequest.getPhotoData());
        }
        book.setISBN(bookRequest.getISBN());
        book.setDescription(bookRequest.getDescription());
        book.setLanguage(bookRequest.getLanguage());
        return book;
    }

}
