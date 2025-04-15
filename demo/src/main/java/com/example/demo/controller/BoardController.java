package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
@RequestMapping("/board")
public class BoardController {
    @GetMapping("/create")
    public void getCreate() {
        // return "board/create";
    }

    @PostMapping("/create")
    // public String postCreate(@ModelAttribute("name") String name,
    // RedirectAttributes rttr) {
    public void postCreate(String name, HttpSession session) {
        log.info("name 값 {}", name);

        session.setAttribute("name1", name);
        // 다른 페이지에서 name을 유지시키기
        // 세션, commend 객체나 modelAttr
        // return "board/list";

        // redirect하며 값 유지
        // rttr.addAttribute("name", name);
        // rttr.addFlashAttribute("name", name);
        // return "redirect:/board/list";
    }

    @GetMapping("/list")
    public void getList() {
        // return "board/list";
    }

    @GetMapping("/read")
    public void getRead() {
        // return "board/read";
    }

    @GetMapping("/update")
    public void getUpdate() {
        // return "board/update";
    }

}
