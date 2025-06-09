package com.example.reply.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.reply.dto.ReplyDTO;
import com.example.reply.entity.Recommendation;
import com.example.reply.entity.Reply;
import com.example.reply.repository.RecommendRepository;
import com.example.reply.service.RecommendService;
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

    private final RecommendService recommendService;
    private final ReplyService replyService;
    private final RecommendRepository recommendRepository;

    @GetMapping("/movie/{mno}")
    public List<ReplyDTO> getMovieReplies(@PathVariable Long mno) {
        log.info("{}번 영화 댓글 요청", mno);
        return replyService.movieReplies(mno);
    }

    @GetMapping("/movie/recommend/{id}")
    public Recommendation getReplyRecommend(@PathVariable Long id) {
        log.info("댓글 추천 w 요청: {}", id);
        return recommendRepository.findById(id).get();
    }
    // @GetMapping("/game/{mno}")
    // public List<Reply> getGameReplies(@PathVariable Long mno) {
    // log.info("{}번 게임 댓글 요청", mno);
    // return replyService.gameReplies(mno);
    // }

    @PutMapping("/movie/update")
    public ReplyDTO putReply(@RequestBody ReplyDTO dto) {
        log.info("댓글 내용 수정 요청: {}", dto);

        return replyService.updateReply(dto);
    }

    @PutMapping("/movie/recommend")
    public void putReplyRecommend(@RequestBody ReplyDTO dto) {
        log.info("댓글 추천 요청: {}", dto);

        recommendService.recommendReply(dto.getRno(), dto.getReplyerId());
    }

    @PostMapping("/movie/new")
    public Long postMovie(@RequestBody ReplyDTO dto) {
        log.info("댓글 추가 요청: {}", dto);
        return replyService.insert(dto).getRno();
    }

    @PostMapping("/movie/re")
    public Long postMovieRecoment(@RequestBody ReplyDTO dto) {
        log.info("대댓글 추가 요청: {}", dto);
        return replyService.rereplyInsert(dto).getRno();
    }

    @DeleteMapping("/movie/{id}")
    public void deleteReply(@PathVariable Long id) {
        replyService.deleteReply(id);
    }

}
