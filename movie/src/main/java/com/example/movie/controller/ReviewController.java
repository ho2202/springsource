package com.example.movie.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.dto.ReviewDTO;
import com.example.movie.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/{mno}")
    public Long postReview(@PathVariable Long mno, @RequestBody ReviewDTO reviewDTO) {
        log.info("리뷰 등록 {}", reviewDTO);
        Long rno = reviewService.insertReview(reviewDTO);
        return rno;
    }

    @GetMapping("/{mno}/{rno}")
    public ReviewDTO getReview(@PathVariable Long rno) {
        return reviewService.getReview(rno);
    }

    @PutMapping("/{mno}/{rno}")
    public ReviewDTO putReview(@PathVariable Long rno, @RequestBody ReviewDTO reviewDTO) {
        log.info("리뷰 수정 {}, {}", rno, reviewDTO);
        ReviewDTO updaReviewDTO = reviewService.updateReview(reviewDTO);
        return updaReviewDTO;
    }

    @DeleteMapping("/{mno}/{rno}")
    public Long getDelete(@PathVariable Long rno) {
        log.info("리뷰 삭제 {}", rno);
        reviewService.removeReplies(rno);
        return rno;
    }

    @GetMapping("/{mno}/all")
    public List<ReviewDTO> getList(@PathVariable Long mno) {
        log.info("요청 {}", mno);
        return reviewService.getReplies(mno);
    }
}
