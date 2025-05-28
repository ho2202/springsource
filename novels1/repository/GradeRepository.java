package com.example.novels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.novels.entity.Grade;
import com.example.novels.entity.Novel;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("delete from Grade g where g.novel = :novel")
    void deleteByNovel(Novel novel);
}
