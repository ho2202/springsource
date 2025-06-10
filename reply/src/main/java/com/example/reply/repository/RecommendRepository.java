package com.example.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reply.entity.Recommendation;
import java.util.List;
import java.util.Optional;

import com.example.reply.entity.Reply;
import com.example.reply.entity.User;

public interface RecommendRepository extends JpaRepository<Recommendation, Long> {
    Optional<Recommendation> findByReplyAndUser(Reply reply, User user);

    boolean existsByUserAndReply(User user, Reply reply);
}
