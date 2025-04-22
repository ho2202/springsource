package com.example.relation.repositiory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.relation.entity.team.Team;
import com.example.relation.entity.team.TeamMember;
import com.example.relation.repository.TeamMemberRepository;
import com.example.relation.repository.TeamRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class TeamRepositoryTest {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Test
    public void insertTest() {
        // 팀 생성
        Team team = teamRepository.save(Team.builder().teamName("team2").build());
        // 팀멤머로
        teamMemberRepository.save(TeamMember.builder().userName("user1").team(team).build());
    }

    @Test
    public void insertTest2() {
        // 팀 찾아오기
        Team team = teamRepository.findById(1L).get();
        // 팀멤버로
        teamMemberRepository.save(TeamMember.builder().userName("user1").team(team).build());
    }

    @Test
    public void readTest1() {
        Team team = teamRepository.findById(1L).get();
        TeamMember teammember = teamMemberRepository.findById(1L).get();
        System.out.println(team);
        System.out.println(teammember);
    }

    @Test
    public void readTest2() {
        TeamMember teammember = teamMemberRepository.findById(1L).get();
        System.out.println(teammember);
        System.out.println(teammember.getTeam());
    }

    @Test
    public void updateTest() {
        TeamMember member = teamMemberRepository.findById(1L).get();
        Team team = teamRepository.findById(2L).get();
        member.setTeam(team);
        teamMemberRepository.save(member);
    }

    @Test
    public void deleteTest() {
        // teamRepository.deleteById(1L);
        // 자식 삭제 후 부모 삭제
        teamMemberRepository.findById(2L).get();
        teamRepository.findById(2L).get();

        teamRepository.deleteById(1L);
    }

    // @Transactional
    @Test
    public void readByTest1() {
        Team team = teamRepository.findById(2L).get();
        System.out.println(team);
        team.getMembers().forEach(member -> System.out.println(member));
    }

    // cascade : 영속성 전이
    @Test
    public void insertTest3() {
        Team team = Team.builder().teamName("team3").build();
        TeamMember teamMember = TeamMember.builder().userName("홍길동").team(team).build();
        // 팀에 멤버 추가 후 저장
        team.getMembers().add(teamMember);
        teamRepository.save(team);
    }

    @Test
    public void deleteTest2() {
        teamRepository.deleteById(3L);
    }
}
