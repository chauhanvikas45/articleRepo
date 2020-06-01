package com.book.controller;

import com.book.dto.BookDTO;
import com.book.dto.TagMatrix;
import com.book.dto.TimeToReadResponseDTO;
import com.book.model.Book;
import com.book.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/articles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addBook(@Valid @RequestBody Book book) {
        Book response;
        // try{
        response = bookService.addBook(book);
        /*}catch (RuntimeException rex){

            return new ResponseEntity(rex.getMessage(),HttpStatus.BAD_REQUEST);
        }*/
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateBook(@RequestBody BookDTO bookDTO, @PathVariable UUID id) {
        Book response = null;
        try {
            response = bookService.updateBook(bookDTO, id);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBook(@Valid @PathVariable UUID id) {
        Book response = bookService.getBook(id);
        return new ResponseEntity(response, HttpStatus.OK);

    }

    @RequestMapping(value = "/articles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Book> getBooks(Pageable pageable) {
        return bookService.getBooks(pageable);
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
        return new ResponseEntity("book deleted : " + id, HttpStatus.OK);
    }

    @RequestMapping(value = "/articles/tags", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTagMatrix() {
        List<TagMatrix> response = bookService.getTagMatrix();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/articles/{id}/timetoread", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTimeToRead(@PathVariable UUID id) {
        TimeToReadResponseDTO response = bookService.fetchTimeToRead(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
