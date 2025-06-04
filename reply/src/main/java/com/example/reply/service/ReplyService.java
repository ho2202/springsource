package com.example.reply.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.reply.dto.ReplyDto;
import com.example.reply.entity.Member;
import com.example.reply.entity.Movie;
import com.example.reply.entity.Reply;
import com.example.reply.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;

    public Reply insert(ReplyDto dto) {
        Reply reply = Reply.builder()
                .movie(Movie.builder().mno(dto.getMno()).build())
                .text("reply")
                .replyer(Member.builder().mno(dto.getReplyer()).build())
                .build();
        return replyRepository.save(reply);
    }

    public Reply rereplyInsert(ReplyDto dto) {

        if (replyRepository.findById(dto.getRef()).isPresent()) {
            Reply reply = Reply.builder()
                    .movie(Movie.builder().mno(dto.getMno()).build())
                    .text("rere")
                    .replyer(Member.builder().mno(dto.getReplyer()).build())
                    .ref(dto.getRef())
                    .build();
            return replyRepository.save(reply);
        }
        return null;
    }

    public List<Reply> selectReplies(Long rno) {
        List<Reply> list = replyRepository.findByRef(rno);
        return list;
    }
}
