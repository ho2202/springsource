package com.example.security;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.security.entity.ClubMember;
import com.example.security.entity.ClubMemberRole;
import com.example.security.repository.ClubMemberRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class SecurityTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClubMemberRepository cMemberRepository;

    @Test
    public void testEncoder() {
        // raw password
        String password = "1111";

        String encodePassword = passwordEncoder.encode(password);
        System.out.println("password: " + password + " , encodePassword : " + encodePassword);
        System.out.println("오류: " + passwordEncoder.matches("2222", encodePassword));
        System.out.println("일치: " + passwordEncoder.matches("1111", encodePassword));
    }

    @Test
    // @Transactional
    public void testRead() {
        ClubMember cm = cMemberRepository.findByEmailAndFromSocial("1e@mail.com", false);
        System.out.println(cm);
    }

    @Test
    public void testInsert() {
        // 모든 회원에게 user, 9와 10번은 다른 권한도
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ClubMember cm = ClubMember.builder().email("e" + i + "@mail.com").name("user" + i)
                    .password(passwordEncoder.encode("1111"))
                    .fromSocial(false).build();
            cm.addMemberRole(ClubMemberRole.USER);
            if (i > 8) {
                cm.addMemberRole(ClubMemberRole.MANAGER);
            }
            if (i > 9) {
                cm.addMemberRole(ClubMemberRole.ADMIN);
            }
            cMemberRepository.save(cm);
        });
    }
}
