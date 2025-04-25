package com.example.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.book.dto.BookDTO;
import com.example.book.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/list")
    public void getList(Model model) {
        log.info("book list 요청");
        List<BookDTO> books = bookService.readAll();
        model.addAttribute("books", books);
    }

    @GetMapping("/create")
    public void getCreate(@ModelAttribute("book") BookDTO bookDTO) {
        log.info("작성 폼 get 요청");
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute("book") @Valid BookDTO bookDTO, BindingResult result,
            RedirectAttributes rttr) { // 변수 이름 지정,
        // post
        // 검증(validation),
        // BindingResult
        if (result.hasErrors()) {
            return "/book/list";
        }
        log.info("book create 폼 요청 {}", bookDTO);
        Long code = bookService.insert(bookDTO);
        // session을 이용
        rttr.addFlashAttribute("code", code);
        return "redirect:/book/list";
    }

    @GetMapping({ "/read", "/modify" })
    public void getRead(Long code, Model model) {
        log.info("get 요청 {}", code);
        BookDTO bookDTO = bookService.read(code);
        model.addAttribute("book", bookDTO);
    }

    @PostMapping("/modify")
    public String getModify(BookDTO bookDTO, RedirectAttributes rttr) {
        log.info("Modify 요청 {}", bookDTO);
        bookService.modify(bookDTO);
        rttr.addAttribute("code", bookDTO.getCode());
        return "redirect:/book/read";
    }

    @PostMapping("/remove")
    public String postRemove(Long code) {
        log.info("Remove 요청 {}", code);
        bookService.remove(code);
        return "redirect:/book/list";
    }

}
