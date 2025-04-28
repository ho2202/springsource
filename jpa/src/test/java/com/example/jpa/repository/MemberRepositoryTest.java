package com.example.jpa.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.example.jpa.entity.Member;
import com.example.jpa.entity.QMember;
import com.example.jpa.entity.Member.RoleType;

@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(0, 50).forEach(i -> {
            Member member = Member.builder().name("홍길동" + i).type(RoleType.USER).age(i + 10).description("user" + i)
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    public void queryDslTest() {
        QMember member = QMember.member;
        System.out.println(memberRepository.findAll(member.name.eq("홍길동3")));
        System.out.println(memberRepository.findAll(member.age.between(15, 20)));
        System.out.println(memberRepository.findAll(member.name.contains("길동1")));
        System.out.println(memberRepository.findAll(Sort.by("no").descending()));

    }
}
