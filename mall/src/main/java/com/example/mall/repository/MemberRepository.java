package com.example.mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mall.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
