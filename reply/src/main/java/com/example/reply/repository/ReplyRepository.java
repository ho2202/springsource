package com.example.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.reply.entity.Movie;
import com.example.reply.entity.Reply;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select r from Reply r where r.movie = :movie")
    List<Reply> findByMovie(Movie movie);

    List<Reply> findByRef(Long ref);
}
