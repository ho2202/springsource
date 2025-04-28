package com.example.jpa.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.jpa.entity.Board;
import com.example.jpa.entity.QBoard;

@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void queryDslTest() {
        QBoard board = QBoard.board;
        // System.out.println(boardRepository.findAll(board.title.eq("글3")));
        // System.out.println(boardRepository.findAll(board.title.startsWith("글3")));
        // System.out.println(boardRepository.findAll(board.title.endsWith("1")));
        // where b.title like '%title%'
        // System.out.println(boardRepository.findAll(board.title.contains("10")));

        Iterable<Board> boards = boardRepository.findAll(board.title.contains("글3").and(board.bno.gt(3L)),
                Sort.by("bno").descending());
        System.out.println(boards);

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(board.bno.gt(0L), pageable);
        System.out.println("page size " + result.getSize());
        System.out.println("page Total page " + result.getTotalPages());
        System.out.println("page Total elements " + result.getTotalElements());
        System.out.println("page Content " + result.getContent());
    }

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
