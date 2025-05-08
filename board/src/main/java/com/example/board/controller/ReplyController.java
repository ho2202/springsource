package com.example.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.ReplyDTO;
import com.example.board.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/replies")
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/new")
    public Long postReply(@RequestBody ReplyDTO dto) {
        log.info("작성 요청 : {}", dto);
        return replyService.create(dto);
    }

    @GetMapping("/board/{bno}")
    public List<ReplyDTO> getList(@PathVariable Long bno) {
        log.info("댓글 요청 bno : {}", bno);
        return replyService.getList(bno);
    }

    @GetMapping("/{rno}")
    public ReplyDTO getReply(@PathVariable Long rno) {
        log.info("댓글 요청 rno : {}", rno);
        ReplyDTO dto = replyService.getReply(rno);
        return dto;
    }

    @PutMapping("/{rno}")
    public Long put(@PathVariable Long rno, @RequestBody ReplyDTO dto) {
        log.info("수정 요청 : {}", dto);
        return replyService.updateReply(dto);
    }

    @DeleteMapping("/{rno}")
    public void delete(@PathVariable Long rno) {
        log.info("댓글 삭제 요청 : {}", rno);
        replyService.delete(rno);
    }

}
