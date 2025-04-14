package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
@RequestMapping("/member")
public class MemeberController {
    // /member/register
    // /member/login
    // /member/logout
    // /member/change
    @GetMapping("/register")
    public void getRegister() {
        log.info("회원가입");
    }

    @GetMapping("/login")
    public void getLogin() {
        log.info("로그인");
    }

    @GetMapping("/logout")
    public void getLogout() {
        log.info("로그아웃");
    }

    @GetMapping("/change")
    public void getChange() {
        log.info("비밀번호 변경");
    }

}
