package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
public class HomeController {
    @GetMapping("/")
    public String getHome() {
        log.info("home 요청");
        return "home"; // 템플릿 파일 이름 넣어야함
    }

    @GetMapping("/basic")
    public String getBasic() {
        return "info";
    }

    @PostMapping("/basic")
    public String postAdd(@ModelAttribute("num1") int num1, @ModelAttribute("num2") int num2, Model model) {
        log.info("덧셈 요청 {}, {}", num1, num2);
        int result = num1 + num2;
        // model.addAttribute("num1", num1);
        // model.addAttribute("num2", num2);
        model.addAttribute("result", result);
        return "info";
    }

}
