package com.example.jpa.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Memo;

@SpringBootTest
public class MemoRepositoryTest {
    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void insertTest() {
        LongStream.rangeClosed(1, 10).forEach(i -> {
            Memo memo = Memo.builder().memoText("memotext").build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void readTest() {
        Memo memo = memoRepository.findById(1L).get();
        System.out.println(memo);
    }

    @Test
    public void updateTest() {
        // Memo memo = Memo.builder().mno(1L).memoText("memoText update").build();
        Memo memo = memoRepository.findById(2L).get();
        memo.changeMemoText("memoText update2");
        memoRepository.save(memo);
    }

    @Test
    public void listTest() {
        memoRepository.findAll().forEach(memo -> System.out.println(memo));
    }

    @Test
    public void deleteTest() {
        memoRepository.deleteById(1L);
    }
}
