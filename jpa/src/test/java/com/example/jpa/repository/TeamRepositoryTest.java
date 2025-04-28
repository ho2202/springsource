package com.example.jpa.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.team.Team;
import com.example.jpa.entity.team.TeamMember;

@SpringBootTest
public class TeamRepositoryTest {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Test
    public void insertTest() {
        // 팀 생성
        Team team = teamRepository.save(Team.builder().teamName("team1").build());
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
    public void findByMemberEqualTeamTest() {
        List<Object[]> result = teamMemberRepository.findTeamMembersByMemberId(2L);

        for (Object[] object : result) {
            System.out.println(Arrays.toString(object));
        }
    }
}
