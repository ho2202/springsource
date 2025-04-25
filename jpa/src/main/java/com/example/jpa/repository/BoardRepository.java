package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jpa.entity.Board;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // List<Board> findByWriter(String writer);

    // List<Board> findByTitle(String title);

    // // 시작하는 값
    // List<Board> findByWriterStartingWith(String wirter);

    // // 포함하는 값
    // List<Board> findByWriterContainingOrContentContaining(String wirter, String
    // content);

    // List<Board> findByWriterContainingAndContentContaining(String wirter, String
    // content);

    // // 끝나는 값
    // List<Board> findByWriterEndingWith(String wirter);

    // List<Board> findByBnoGreaterThan(Long bno);

    // List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);

    // Query entity로 테이블
    @Query("select b from Board b where b.writer = ?1")
    List<Board> findByWriter(String writer);

    @Query("select b from Board b where b.writer like ?1%")
    List<Board> findByWriterStartingWith(String writer);

    @Query(value = "select * from Board b where b.bno > ?1", nativeQuery = true)
    List<Board> findByBnoGreaterThan(Long bno);

}
