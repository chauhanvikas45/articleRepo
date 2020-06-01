package com.book.service;

import com.book.dto.BookDTO;
import com.book.dto.TagMatrix;
import com.book.dto.TimeToRead;
import com.book.dto.TimeToReadResponseDTO;
import com.book.exception.BadRequestException;
import com.book.model.Book;
import com.book.repository.BookRepository;
import com.book.utils.BookUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


@Service
public class BookServiceImpl implements BookService {
    @Value("${averageSpeed:10}")
    private Long speed;

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        book.setTagList(BookUtils.toLowerCase(book.getTagList()));
        if (null == book.getSlug()) {
            book.setSlug(book.getTitle().toLowerCase().replace(" ", "-"));
        }
        if (bookRepository.findByBody(book.getBody()).size() != 0) {
            throw new BadRequestException("body already exist");
        }

        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(BookDTO bookDTO, UUID id) throws InvocationTargetException, IllegalAccessException {
        Book book = bookRepository.findById(id).get();
        BeanUtilsBean notNull = new BookUtils();
        notNull.copyProperties(book, bookDTO);
        //BeanUtils.copyProperties(bookDTO,book);
        System.out.println(book);
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book getBook(UUID id) {
        Optional<Book> bookEntity = bookRepository.findById(id);
        return bookEntity.get();
    }

    @Override
    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public void deleteBook(UUID id) {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new BadRequestException("book not found for Id : " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<TagMatrix> getTagMatrix() {
        Map<String, Integer> map = new HashMap<>();
        Iterable<Book> bookList = bookRepository.findAll();
        int initialValue = 1;
        for (Book book : bookList) {
            String[] tags = book.getTagList();
            if (null != tags) {
                for (int i = 0; i < tags.length; i++) {
                    if (map.containsKey(tags[i])) {
                        Integer currentValue = map.get(tags[i]) + 1;
                        map.put(tags[i], currentValue);
                    } else {
                        map.put(tags[i], initialValue);
                    }
                }
            }

        }
        return getTagListOccurance(map);
    }

    private List<TagMatrix> getTagListOccurance(Map<String, Integer> map) {
        List<TagMatrix> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            TagMatrix tagMatrix = new TagMatrix();
            tagMatrix.setTag(entry.getKey());
            tagMatrix.setOccurance(entry.getValue().toString());
            list.add(tagMatrix);
        }
        return list;
    }

    @Override
    public TimeToReadResponseDTO fetchTimeToRead(UUID id) {
        Optional<Book> book = bookRepository.findById(id);
        TimeToReadResponseDTO timeToReadResponseDTO = new TimeToReadResponseDTO();
        timeToReadResponseDTO.setId(id);
        timeToReadResponseDTO.setTimeToRead(calculateTime(book.get()));

        return timeToReadResponseDTO;
    }

    private TimeToRead calculateTime(Book book) {
        Long wordCount = calculateWords(book.toString());
        Long time = wordCount / speed;
        System.out.println(time.intValue());
        TimeToRead timeToRead = new TimeToRead();

        timeToRead.setMinutes(time.intValue());
        return timeToRead;
    }

    private Long calculateWords(String input) {
        return Long.valueOf(input.split(" ").length);
    }


}