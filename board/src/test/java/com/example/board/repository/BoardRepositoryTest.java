package com.example.board.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.board.dto.PageRequestDTO;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertMemberTest() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@gmail.com")
                    .name("user" + i)
                    .password("123")
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    public void insertBoardTest() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            // int num = i % 10;
            // if (num == 0) {
            // num = 10;
            // }
            int num = (int) (Math.random() * 10) + 1;
            Board board = Board.builder()
                    .title(i + "게시글")
                    .content("글 내용" + i)
                    .member(memberRepository.findById("user" + num + "@gmail.com").get())
                    .build();
            boardRepository.save(board);
        });
    }

    @Test
    public void insertReplyTest() {
        IntStream.rangeClosed(1, 40).forEach(i -> {
            long num = (int) (Math.random() * 100) + 1;

            Reply reply = Reply.builder()
                    .board(boardRepository.findById(num).get())
                    .text("Reply~..." + i)
                    .replayer("guest" + i)
                    .build();
            replyRepository.save(reply);
        });
    }

    @Test
    @Transactional
    public void readBoardTest() {
        Board board = boardRepository.findById(162L).get();
        System.out.println(board.getMember());
        System.out.println(board.getReplies());
    }

    @Test
    @Transactional
    public void readReplyTest() {
        Reply reply = replyRepository.findById(2L).get();
        System.out.println(reply.getBoard());
    }

    @Test
    public void listQuerydslTest() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(0).size(10)
                .type("tc").keyword("title").build();
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.list(pageRequestDTO.getType(), pageRequestDTO.getKeyword(), pageable);

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }
        // for (Object[] objects : list) {
        // Board board = (Board) objects[0];
        // Member member = (Member) objects[1];
        // Long replyCount = (Long) objects[2];
        // System.out.println(board);
        // System.out.println(member);
        // System.out.println(replyCount);
        // }
    }

    @Test
    public void rowTest() {
        Object[] result = boardRepository.getBoardByBno(5L);
        System.out.println(Arrays.toString(result));
    }
}
