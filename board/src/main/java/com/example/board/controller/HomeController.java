package com.example.board.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public String getHome() {
        return "redirect:/board/list";
    }

}
