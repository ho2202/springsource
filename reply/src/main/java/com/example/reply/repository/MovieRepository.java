package com.example.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reply.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
