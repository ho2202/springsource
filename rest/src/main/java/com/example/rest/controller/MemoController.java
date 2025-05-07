package com.example.rest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.rest.dto.MemoDTO;
import com.example.rest.service.MemoService;

@Log4j2
@RestController
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemoController {
    // 주소 설계
    // 전체 memo 조회 : /memo/list
    // 특정 메모 조회 /memo/read?mno=1
    // 수정: /memo/update?mno=1
    // 추가: /memo/new
    // 삭제: /memo/remove?mno=1

    private final MemoService memoService;

    @GetMapping("/list")
    public List<MemoDTO> getList(Model model) {
        List<MemoDTO> list = memoService.getList();
        return list;
    }

    // @GetMapping("/read")
    // public void getRow(Long mno, Model model) {
    // log.info("read 요청 {}", mno);
    // MemoDTO dto = memoService.getRow(mno);
    // model.addAttribute("dto", dto);
    // }

    @GetMapping("/new")
    public void getNew() {
        log.info("새 메모 작성 폼 요청");
    }

    @PostMapping("/new")
    public Long postNew(@RequestBody MemoDTO memoDTO) {
        log.info("새 메모 작성 : {}", memoDTO);
        Long mno = memoService.memoCreate(memoDTO);
        return mno;
    }

    @GetMapping({ "/read", "/update" })
    public MemoDTO getUpdate(Long mno, Model model) {
        MemoDTO memodto = memoService.getRow(mno);
        // model.addAttribute("dto", memodto);
        log.info("메모 수정 요청");
        return memodto;
    }

    @PutMapping("/update")
    public Long putUpdate(@RequestBody MemoDTO dto) {
        log.info("메모 수정 {}", dto);
        Long mno = memoService.memoUpdate(dto);
        return mno;
    }

    @DeleteMapping("/remove/{mno}")
    public Long getRemove(@PathVariable Long mno) {
        memoService.memoDelete(mno);
        log.info(mno + "메모 삭제 : {}", mno);
        return mno;
    }
}
