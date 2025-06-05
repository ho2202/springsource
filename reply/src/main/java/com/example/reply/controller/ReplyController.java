package com.example.reply.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.reply.dto.ReplyDTO;
import com.example.reply.entity.Reply;
import com.example.reply.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyController {
    private final ReplyService replyService;

    @GetMapping("/movie/{mno}/all")
    public List<ReplyDTO> getMovieReplies(@PathVariable Long mno) {
        log.info("{}번 영화 댓글 요청", mno);
        return replyService.movieReplies(mno);
    }

    // @GetMapping("/game/{mno}")
    // public List<Reply> getGameReplies(@PathVariable Long mno) {
    // log.info("{}번 게임 댓글 요청", mno);
    // return replyService.gameReplies(mno);
    // }
    @PostMapping("/movie/{mno}")
    public Long postMovie(@RequestBody ReplyDTO dto) {
        log.info("댓글 추가 요청: {}", dto);
        return replyService.insert(dto).getRno();
    }

    @PostMapping("/movie/{mno}/re")
    public Long postMovieRecoment(@RequestBody ReplyDTO dto) {
        log.info("대댓글 추가 요청: {}", dto);
        return replyService.rereplyInsert(dto).getRno();
    }

}
