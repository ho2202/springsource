package com.example.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.book.dto.BookDTO;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@Controller
@RequestMapping("/book")
public class BookController {

    @GetMapping("/list")
    public void getList() {
        log.info("book list 요청");
    }

    @GetMapping("/create")
    public void getCreate(BookDTO bookDTO) {
        log.info("book create요청 {}", bookDTO);
    }

    @GetMapping({ "/read", "/modify" })
    public String getRead(Long code) {
        log.info("get 요청 {}", code);
        return new String();
    }

    @PostMapping("/modify")
    public String getModify(BookDTO bookDTO) {
        log.info("Modify 요청 {}", bookDTO);
        return new String();
    }

}
