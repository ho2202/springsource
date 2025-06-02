package com.example.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reply.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
