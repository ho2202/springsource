package com.example.book.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.book.dto.BookDTO;
import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public Long insert(BookDTO bookDTO) {
        bookRepository.save(mapping(bookDTO));
        return bookDTO.getCode();
    }

    public BookDTO read(Long code) {
        Book book = bookRepository.findById(code).get();
        return mapping(book);
    }

    public void remove(Long code) {
        bookRepository.deleteById(code);
    }

    public void modify(BookDTO bookDTO) {
        Book book = bookRepository.findById(bookDTO.getCode()).get();
        book.setPrice(bookDTO.getPrice());
        bookRepository.save(book);
    }

    public List<BookDTO> readAll() {
        List<Book> bookList = bookRepository.findAll();
        List<BookDTO> books = bookList.stream().map(book -> mapping(book))
                .collect(Collectors.toList());
        return books;
    }

    public Book mapping(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    public BookDTO mapping(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }
}
