package com.example.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jpa.entity.team.Team;
import com.example.jpa.entity.team.TeamMember;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    // team 기준으로 팀 정보 조회
    List<TeamMember> findByTeam(Team team);

    // team 기준으로 멤버와 팀 정보 조회
    @Query("select m,t from TeamMember m join m.team t where t.id = :teamId") // on 구문이 필요 없음
    List<Object[]> findTeamMembersByMemberId(Long id);

    // QueryDSL 이용
}
