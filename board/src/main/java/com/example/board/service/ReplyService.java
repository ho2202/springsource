package com.example.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.board.dto.ReplyDTO;
import com.example.board.entity.Board;
import com.example.board.entity.Reply;
import com.example.board.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;

    public Long create(ReplyDTO dto) {
        Reply reply = dtoToEntity(dto);
        return replyRepository.save(reply).getRno();
    }

    public List<ReplyDTO> getList(Long bno) {
        Board board = Board.builder().bno(bno).build();
        List<Reply> result = replyRepository.findByBoardOrderByRno(board);

        return result.stream().map(reply -> entityToDto(reply)).collect(Collectors.toList());
    }

    public ReplyDTO getReply(Long rno) {
        Reply reply = replyRepository.findById(rno).get();
        return entityToDto(reply);
    }

    public Long updateReply(ReplyDTO dto) {
        Reply reply = replyRepository.findById(dto.getRno()).get();
        reply.changeText(dto.getText());
        return replyRepository.save(reply).getRno();
    }

    public void delete(Long rno) {
        replyRepository.deleteById(rno);
    }

    private ReplyDTO entityToDto(Reply reply) {
        ReplyDTO dto = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .createdDate(reply.getCreatedDate())
                .replyer(reply.getReplyer())
                .createdDate(reply.getCreatedDate())
                .build();
        return dto;
    }

    private Reply dtoToEntity(ReplyDTO dto) {
        Reply reply = Reply.builder()
                .rno(dto.getRno()).text(dto.getText()).replyer(dto.getReplyer())
                .board(Board.builder().bno(dto.getBno()).build()).build();
        return reply;
    }
}
