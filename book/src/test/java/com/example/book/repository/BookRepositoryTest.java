package com.example.book.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.book.entity.Book;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testInsert() {
        IntStream.range(1, 10).forEach(i -> {
            bookRepository.save(Book.builder().author("author" + i).title("책" + i).price(10 + i).build());
        });
    }

    @Test
    public void testList() {
        bookRepository.findAll().forEach(book -> System.out.println(book));
    }

    @Test
    public void testList2() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("code").descending());
        Page<Book> result = bookRepository.findAll(pageable);
        result.getContent().forEach(book -> System.out.println(book));
        System.out.println("전체 행 " + result.getTotalElements());
        System.out.println("전체 페이지 수 " + result.getTotalPages());
    }

    @Test
    public void testGet() {
        System.out.println(
                bookRepository.findById(1L).get());
    }

    @Test
    public void testUpdate() {
        Book book = bookRepository.findById(3L).get();
        book.setPrice(20000);
        bookRepository.save(book);
    }

    @Test
    public void testRemove() {
        Long id = 2L;
        bookRepository.deleteById(id);
        log.info("삭제 id {}", id);
    }
}
