package com.example.movie.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.movie.dto.ReviewDTO;
import com.example.movie.entity.Member;
import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;
import com.example.movie.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Long insertReview(ReviewDTO reviewDTO) {
        Review review = dtoToEntity(reviewDTO);
        return reviewRepository.save(review).getRno();
    }

    public ReviewDTO getReview(Long rno) {
        Review review = reviewRepository.findById(rno).get();
        return entityToDto(review);
    }

    public ReviewDTO updateReview(ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(reviewDTO.getRno()).orElseThrow();

        review.changeGrade(reviewDTO.getGrade());
        review.changeText(reviewDTO.getText());
        review = reviewRepository.save(review);

        return entityToDto(review);
    }

    public List<ReviewDTO> getReplies(Long mno) {
        Movie movie = Movie.builder().mno(mno).build();
        List<Review> result = reviewRepository.findByMovie(movie);

        List<ReviewDTO> list = result.stream().map(review -> entityToDto(review)).collect(Collectors.toList());
        return list;
    }

    public void removeReplies(Long rno) {
        reviewRepository.deleteById(rno);
    }

    private Review dtoToEntity(ReviewDTO dto) {
        Review review = Review.builder()
                .rno(dto.getRno())
                .grade(dto.getGrade())
                .text(dto.getText())
                .member(Member.builder().mid(dto.getMid()).build())
                .movie(Movie.builder().mno(dto.getMno()).build())
                .build();
        return review;
    }

    private ReviewDTO entityToDto(Review review) {
        ReviewDTO dto = ReviewDTO.builder()
                .rno(review.getRno())
                .text(review.getText())
                .grade(0)
                .updatedDate(review.getUpdatedDate())
                .createdDate(review.getCreatedDate())

                .mid(review.getMember().getMid())
                .email(review.getMember().getEmail())
                .nickname(review.getMember().getNickname())
                .build();
        return dto;
    }
}
