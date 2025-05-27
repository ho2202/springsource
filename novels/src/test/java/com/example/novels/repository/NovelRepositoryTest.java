package com.example.novels.repository;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.novels.entity.Genre;
import com.example.novels.entity.Grade;
import com.example.novels.entity.Member;
import com.example.novels.entity.Novel;

@SpringBootTest
public class NovelRepositoryTest {

    @Autowired
    private NovelRepository novelRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void getNovelTest() {
        Object[] result = novelRepository.getNovelById(2L);
        Novel novel = (Novel) result[0];
        Genre genre = (Genre) result[1];
        Double avgGrade = (Double) result[2];

        System.out.println(novel);
        System.out.println(genre);
        System.out.println(avgGrade);

        System.out.println(Arrays.toString(result));
    }

    @Test
    public void getNovelListTest() {
        Pageable pageable = PageRequest.of(1, 10, Sort.by("id").descending());

        Page<Object[]> result = novelRepository.list(pageable);
        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testInsertMember() {
        IntStream.rangeClosed(1, 50).forEach(i -> {
            Member member = Member.builder().email("e" + i + "@mail.com").pw("1111").build();
            memberRepository.save(member);
        });
    }

    @Test
    public void testInsertGrade() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            long nid = (long) (Math.random() * 30) + 1;
            int rating = (int) (Math.random() * 5) + 1;
            int uid = (int) (Math.random() * 50) + 1;

            Grade grade = Grade.builder().novel(Novel.builder().id(nid).build())
                    .member(Member.builder().email("e" + uid + "@mail.com").build()).rating(rating).build();

            gradeRepository.save(grade);
        });
    }
}
