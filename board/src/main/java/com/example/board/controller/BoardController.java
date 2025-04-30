package com.example.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResultDTO;
import com.example.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
@Log4j2
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public void getList(Model model, PageRequestDTO pageRequestDTO) {
        log.info("List 요청");
        PageResultDTO<BoardDTO> list = boardService.getList(pageRequestDTO);
        model.addAttribute("result", list);
    }

    @GetMapping({ "/read", "/modify" })
    public void getRead(Long bno, Model model) {
        log.info("get 요청 {}", bno);
        BoardDTO dto = boardService.getRow(bno);
        model.addAttribute("dto", dto);

    }

    @PostMapping("/read")
    public String postRead() {

        return "";
    }

}
