package com.example.board.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.service.BoardService;

import lombok.extern.log4j.Log4j2;

@RequestMapping("/board")
@Controller
@Log4j2
public class BoardController {
    private BoardService boardService;

    @GetMapping("/list")
    public void getList() {
        log.info("List 요청");
    }

}
