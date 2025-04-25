package com.example.jpa.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Board;

@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void queryMethodTest() {
        // System.out.println(boardRepository.findByWriter("홍길동1"));
        // System.out.println(boardRepository.findByTitle("글3"));
        System.out.println(boardRepository.findByWriterStartingWith("홍"));
        // System.out.println(boardRepository.findByWriterContainingAndContentContaining("1",
        // "홍"));
        // System.out.println(boardRepository.findByWriterContainingAndContentContaining("1",
        // "내용"));
        // System.out.println(boardRepository.findByWriterEndingWith("1"));
        // System.out.println(boardRepository.findByBnoGreaterThan(7L));
        // System.out.println(boardRepository.findByBnoGreaterThanOrderByBnoDesc(7L));
        // System.out.println(boardRepository.findByWriter("홍길동1"));
    }

    @Test
    public void insertTest() {
        LongStream.rangeClosed(1, 10).forEach(i -> {
            Board board = Board.builder().title("글" + i).content("내용").writer("홍길동").build();
            boardRepository.save(board);
        });
    }

    @Test
    public void updateTest() {
        Board board = boardRepository.findById(1L).get();
        board.setContent("content update");
        boardRepository.save(board);
    }

    @Test
    public void readTest() {
        Board board = boardRepository.findById(2L).get();
        System.out.println(board);
    }

    @Test
    public void selectTest() {
        boardRepository.findAll().forEach(board -> System.out.println(board));
    }

    @Test
    public void deleteTest() {
        boardRepository.deleteById(2L);
    }
}
