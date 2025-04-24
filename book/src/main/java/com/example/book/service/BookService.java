package com.example.book.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.book.dto.BookDTO;
import com.example.book.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public void insert(BookDTO bookDTO) {
    }

    public void read(Long code) {
    }

    public void remove(Long code) {
    }

    public void modify(BookDTO bookDTO) {
    }

    public void list(BookDTO bookDTO) {
    }

}
