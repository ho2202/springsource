package com.example.relation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.relation.entity.team.TeamMember;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

}
