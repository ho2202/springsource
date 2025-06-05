package com.example.reply.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.reply.entity.Movie;
import com.example.reply.entity.Reply;

import lombok.extern.log4j.Log4j2;

import com.example.reply.entity.Member;

@Log4j2
@SpringBootTest
public class ReplyRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    void first() {
        Member me = Member.builder().build();
        memberRepository.save(me);
        Movie movie = Movie.builder().title("title1").build();
        movieRepository.save(movie);
        me = Member.builder().build();
        memberRepository.save(me);
        movie = Movie.builder().title("title2").build();
        movieRepository.save(movie);
    }

    @Test
    void testInsertReply() {
        Movie movie = Movie.builder().mid(1L).build();
        Reply reply = Reply.builder()
                .replyer(Member.builder().userCode(1L).build())
                .movie(movie)
                .text("test1")
                .build();
        replyRepository.save(reply);
    }

    @Test
    void testInsertReReply() {
        Movie movie = Movie.builder().mid(1L).build();
        Reply reply = Reply.builder()
                .replyer(Member.builder().userCode(2L).build())
                .movie(movie)
                .text("test reply")
                .ref(11l)
                .mention(1L)
                .build();
        replyRepository.save(reply);
    }

    @Test
    void testselete() {
        // Long id = movieRepository.findById(1l).get().getMno();
        Movie movie = movieRepository.findById(1l).get();
        List<Reply> list = replyRepository.findByMovie(movie);

        list.forEach(elem -> {
            // if (elem.getRef() != null) {
            // List<Reply> rereplies = replyRepository.findByRef(elem.getRef());
            // log.info(rereplies);
            // }
            log.info(elem);
        });
    }

    @Test
    private void testInsert() {
    }
}
