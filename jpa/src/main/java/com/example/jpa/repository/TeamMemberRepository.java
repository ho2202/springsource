package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.entity.team.TeamMember;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

}
