package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.board.entity.Board;
import com.example.board.entity.Reply;
import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Modifying // delete, update 쿼리에 필요함
    @Query("delete from Reply r where r.board.bno = :bno")
    void deleteByBoardBno(Long bno);

    // board 에 달린 댓글 rno 순서로 모두 가져오기
    List<Reply> findByBoardOrderByRno(Board board);
}
