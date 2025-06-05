package com.example.reply.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.reply.dto.ReplyDTO;
import com.example.reply.entity.Member;
import com.example.reply.entity.Movie;
import com.example.reply.entity.Reply;
import com.example.reply.repository.MemberRepository;
import com.example.reply.repository.MovieRepository;
import com.example.reply.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final MovieRepository movieRepository;
    private final MemberRepository memberRepository;

    public Reply insert(ReplyDTO dto) {
        // Reply reply = Reply.builder()
        // .movie(Movie.builder().mno(dto.getMno()).build())
        // .text("reply")
        // .replyer(Member.builder().mno(dto.getReplyer()).build())
        // .build();
        return replyRepository.save(dtoToEntity(dto));
    }

    // 대댓글 작성
    public Reply rereplyInsert(ReplyDTO dto) {
        // 대댓글을 달 댓글이 존재하지 않으면 널 반환
        Optional<Reply> replyOp = replyRepository.findById(dto.getRef());
        log.info("뭐야 {}", replyOp.isPresent());
        log.info("뭐야 {}", dto.getRef());

        if (replyOp.isPresent()) {
            // 멘션할 댓글의 작성자 정보
            dto.setMention(memberRepository.findById(replyOp.get().getMention()).get().getUserName());
            dto.setMentionId(replyOp.get().getMention());
            return replyRepository.save(dtoToEntity(dto));
        }
        return null;
    }

    // 영화의 댓글들 가져오기
    public List<ReplyDTO> movieReplies(Long rno) {
        Movie movie = movieRepository.findById(rno).get();
        List<Reply> list = replyRepository.findByMovie(movie);
        List<ReplyDTO> result = list.stream().map(reply -> entityToDto(reply))
                .collect(Collectors.toList());
        return result;
    }

    private Reply dtoToEntity(ReplyDTO dto) {
        Reply reply = Reply.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .replyer(Member.builder().userCode(dto.getMentionId()).build())
                .movie(Movie.builder().mid(dto.getMno()).build())
                .ref(dto.getRef())
                .mention(dto.getMno())
                .recommend(dto.getRecommend())
                .build();
        return reply;
    }

    private ReplyDTO entityToDto(Reply reply) {
        ReplyDTO dto = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer().getUserName())
                .replyerId(reply.getReplyer().getUserCode())
                .recommend(reply.getRecommend())
                .ref(reply.getRef())
                .createdDate(reply.getCreatedDate())
                .updatedDate(reply.getUpdatedDate())
                .build();
        if (reply.getMention() != null) {
            dto.setMentionId(reply.getMention());
            dto.setMention(memberRepository.findById(reply.getMention()).get().getUserName());
        }
        return dto;
    }
}
