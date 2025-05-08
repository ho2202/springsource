package com.example.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.rest.dto.BookDTO;
import com.example.rest.dto.PageRequestDTO;
import com.example.rest.dto.PageResultDTO;
import com.example.rest.entity.Book;
import com.example.rest.repository.BookRepository;

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

    public PageResultDTO<BookDTO> readAll(PageRequestDTO pageRequestDTO) {
        // List<Book> bookList = bookRepository.findAll();
        // List<BookDTO> books = bookList.stream().map(book -> mapping(book))
        // .collect(Collectors.toList());
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
                Sort.by("code").descending());

        Page<Book> result = bookRepository
                .findAll(bookRepository.makePredicate(pageRequestDTO.getType(), pageRequestDTO.getKeyword()), pageable);
        List<BookDTO> books = result.get().map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
        long totalCount = result.getTotalElements();

        return PageResultDTO.<BookDTO>withAll().dtoList(books).totalCount(totalCount).pageRequestDTO(pageRequestDTO)
                .build();
    }

    public Book mapping(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    public BookDTO mapping(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }
}
