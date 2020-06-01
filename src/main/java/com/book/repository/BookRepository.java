package com.book.repository;

import com.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, UUID> {
    List<Book> findByBody(String body);
}
