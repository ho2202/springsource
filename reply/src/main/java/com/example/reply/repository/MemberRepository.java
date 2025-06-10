package com.example.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reply.entity.User;

public interface MemberRepository extends JpaRepository<User, String> {

}
