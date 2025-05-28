package com.example.novels.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.novels.dto.NovelDTO;
import com.example.novels.dto.PageRequestDTO;
import com.example.novels.dto.PageResultDTO;
import com.example.novels.service.NovelService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("api/books")
public class NovelRestController {
    private final NovelService novelService;

    // add post : 업로드
    @GetMapping("")
    public PageResultDTO<NovelDTO> getList(PageRequestDTO pageRequestDTO) {
        log.info("전체 도서 정보 {}", pageRequestDTO);
        PageResultDTO<NovelDTO> result = novelService.getList(pageRequestDTO);
        return result;
    }

    @PutMapping("/{id}")
    public Long putNovel(@RequestBody NovelDTO novelDTO) {
        log.info("수정 {}", novelDTO);
        return novelService.avaUpdate(novelDTO);
    }

    @PostMapping("/add")
    public Long postNovel(@RequestBody NovelDTO novelDTO) {
        log.info("도서 추가 {}", novelDTO);
        return novelService.novelInsert(novelDTO);
    }

    @DeleteMapping("/{id}")
    public Long removeNovel(@PathVariable Long id) {
        log.info("삭제 {}", id);
        novelService.novelRemove(id);
        return id;
    }

}
