package com.example.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.rest.dto.BookDTO;
import com.example.rest.dto.PageRequestDTO;
import com.example.rest.dto.PageResultDTO;
import com.example.rest.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

// rest 같이 쓰기 : ResponseEntity or @ResponseBody
@Log4j2
@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/list")
    public ResponseEntity<PageResultDTO<BookDTO>> getList(PageRequestDTO pageRequestDTO) {
        log.info("book list 요청 {}", pageRequestDTO);
        PageResultDTO<BookDTO> pageResultDTO = bookService.readAll(pageRequestDTO);
        return new ResponseEntity<>(pageResultDTO, HttpStatus.OK);
    }

    @GetMapping("/create")
    public void getCreate(@ModelAttribute("book") BookDTO bookDTO, PageRequestDTO pageRequestDTO) {
        log.info("작성 폼 get 요청");
    }

    @ResponseBody
    @PostMapping("/create")
    public Long postCreate(@RequestBody BookDTO bookDTO) {
        log.info("book create 폼 요청 {}", bookDTO);
        return bookService.insert(bookDTO);
    }

    @ResponseBody
    @GetMapping({ "/read/{code}", "/modify/{code}" })
    public BookDTO getRead(@PathVariable Long code, PageRequestDTO pageRequestDTO, Model model) {
        log.info("get 요청 {}", code);
        BookDTO bookDTO = bookService.read(code);
        return bookDTO;
    }

    @ResponseBody
    @PutMapping("/modify")
    public Long getModify(@RequestBody BookDTO bookDTO, PageRequestDTO pageRequestDTO) {
        log.info("Modify 요청 {}", bookDTO);
        bookService.modify(bookDTO);
        return bookDTO.getCode();
    }

    @DeleteMapping("/remove/{code}")
    public ResponseEntity<Long> postRemove(@PathVariable Long code, PageRequestDTO pageRequestDTO) {
        log.info("Remove 요청 {}", code);
        bookService.remove(code);
        return new ResponseEntity<Long>(code, HttpStatus.OK);
    }

}
