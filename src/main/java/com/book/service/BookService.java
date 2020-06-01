package com.book.service;


import com.book.dto.BookDTO;
import com.book.dto.TagMatrix;
import com.book.dto.TimeToReadResponseDTO;
import com.book.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

public interface BookService {
    Book addBook(Book book);

    Book updateBook(BookDTO bookDTO, UUID id) throws InvocationTargetException, IllegalAccessException;

    Book getBook( UUID id);

    Page<Book> getBooks(Pageable pageable);

    void deleteBook(UUID id);

    List<TagMatrix> getTagMatrix();

    TimeToReadResponseDTO fetchTimeToRead(UUID id);
}
