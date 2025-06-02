package com.example.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reply.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
