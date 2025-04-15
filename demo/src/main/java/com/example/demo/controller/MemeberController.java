package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.memberDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("mDTO") memberDTO memberDTO, RedirectAttributes rttr) {
        // String userid = memberDTO.getUserid();
        // String password = memberDTO.getPassword();
        // boolean check = memberDTO.isChecwk();
        // 로그인 페이지로
        // rttr.addAttribute("userid", memberDTO.getUserid());
        log.info("회원 가입 요청 {}", memberDTO);
        rttr.addFlashAttribute("userid", memberDTO.getUserid());
        rttr.addFlashAttribute("userid", memberDTO.getPassword());
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public void getLogin() {
        log.info("로그인");
    }

    @PostMapping("/login")
    // public void postLogin(String userid, String password) {
    public void postLogin(HttpServletRequest httpSR) {
        String userid = httpSR.getParameter("userid");
        String password = httpSR.getParameter("password");
        String remote = httpSR.getRemoteAddr();
        String local = httpSR.getLocalAddr();

        log.info("클라이언트 정보: {}, {}", remote, local);
        log.info("로그인 요청 {}, {}", userid, password);
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
