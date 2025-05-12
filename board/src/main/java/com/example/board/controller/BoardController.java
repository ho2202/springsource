package com.example.board.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResultDTO;
import com.example.board.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
@Log4j2
public class BoardController {
    private final BoardService boardService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public void getCreate(@ModelAttribute("dto") BoardDTO boardDTO, PageRequestDTO pageRequestDTO) {
        log.info("create 작성 요청");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String postCreate(@ModelAttribute("dto") @Valid BoardDTO boardDTO, BindingResult result,
            PageRequestDTO pageRequestDTO,
            RedirectAttributes rttr) {
        log.info("create 요청 {}", boardDTO);
        if (result.hasErrors()) {
            return "/board/create";
        }
        boardService.create(boardDTO);

        rttr.addAttribute("page", pageRequestDTO.getPage());
        rttr.addAttribute("size", pageRequestDTO.getSize());
        rttr.addAttribute("type", pageRequestDTO.getType());
        rttr.addAttribute("keyword", pageRequestDTO.getKeyword());
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public void getList(Model model, PageRequestDTO pageRequestDTO, RedirectAttributes rttr) {
        log.info("List 요청");
        PageResultDTO<BoardDTO> list = boardService.getList(pageRequestDTO);
        model.addAttribute("result", list);
    }

    @GetMapping({ "/read", "/modify" })
    public void getRead(Long bno, Model model, PageRequestDTO pageRequestDTO) {
        log.info("get 요청 {}", bno);
        BoardDTO dto = boardService.getRow(bno);
        model.addAttribute("dto", dto);
    }

    @PreAuthorize("authentication.name == #dto.email")
    @PostMapping("/modify")
    public String postModify(BoardDTO dto, PageRequestDTO pageRequestDTO, RedirectAttributes rttr) {
        log.info("modify 요청 {}, {}", dto, pageRequestDTO);
        Long bno = boardService.update(dto);
        rttr.addAttribute("bno", bno);
        rttr.addAttribute("page", pageRequestDTO.getPage());
        rttr.addAttribute("size", pageRequestDTO.getSize());
        rttr.addAttribute("type", pageRequestDTO.getType());
        rttr.addAttribute("keyword", pageRequestDTO.getKeyword());
        return "redirect:/board/read";
    }

    @GetMapping("/remove")
    public String getRemove(Long bno, PageRequestDTO pageRequestDTO, RedirectAttributes rttr) {
        log.info("remove 요청 {}", bno);
        boardService.delete(bno);

        rttr.addAttribute("page", pageRequestDTO.getPage());
        rttr.addAttribute("size", pageRequestDTO.getSize());
        rttr.addAttribute("type", pageRequestDTO.getType());
        rttr.addAttribute("keyword", pageRequestDTO.getKeyword());
        return "redirect:/board/list";
    }

}
