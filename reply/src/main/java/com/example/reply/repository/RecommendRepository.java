package com.example.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reply.entity.Recommendation;
import java.util.List;
import java.util.Optional;

import com.example.reply.entity.Reply;
import com.example.reply.entity.Member;

public interface RecommendRepository extends JpaRepository<Recommendation, Long> {
    Optional<Recommendation> findByReplyAndUser(Reply reply, Member user);

    boolean existsByUserAndReply(Member user, Reply reply);
}
