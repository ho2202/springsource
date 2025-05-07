package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.board.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Modifying // delete, update 쿼리에 필요함
    @Query("delete from Reply r where r.board.bno = :bno")
    void deleteByBoardBno(Long bno);
}
