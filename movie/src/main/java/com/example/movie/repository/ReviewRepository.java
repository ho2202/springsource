package com.example.movie.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Modifying
    @Query("delete from Review r where r.movie = :movie")
    void deleteByMovie(Movie movie);

    // moive의 리뷰 가져오기
    @EntityGraph(attributePaths = "member", type = EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);
}
