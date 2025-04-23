package com.example.mall.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Log4j2
@Controller
public class HomeController {
    @GetMapping("/")
    public String getHome() {
        log.info("home 요청");
        return "home"; // 템플릿 파일 이름 넣어야함
    }

    @PostMapping("/")
    public void postHome(HttpSession session, String username) {
        session.setAttribute("", username);
    }

}
