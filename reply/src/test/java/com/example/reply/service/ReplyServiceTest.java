package com.example.reply.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.reply.dto.ReplyDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    @Test
    void testInsertReply() {
        ReplyDto dto = ReplyDto.builder()
                .text("123")
                .replyer(1L)
                .mno(1L)
                .build();
        replyService.insert(dto);

        dto = ReplyDto.builder()
                .text("nullll")
                .replyer(2L)
                .mno(1L)
                .ref(3L)
                .build();
        replyService.rereplyInsert(dto);

    }

}
