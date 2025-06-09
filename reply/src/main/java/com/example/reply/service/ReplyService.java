package com.example.reply.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.reply.dto.RecommendationDTO;
import com.example.reply.dto.ReplyDTO;
import com.example.reply.entity.Member;
import com.example.reply.entity.Movie;
import com.example.reply.entity.Recommendation;
import com.example.reply.entity.Reply;
import com.example.reply.repository.MemberRepository;
import com.example.reply.repository.MovieRepository;
import com.example.reply.repository.RecommendRepository;
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
    private final RecommendRepository recommendRepository;

    public Reply insert(ReplyDTO dto) {
        // Reply reply = Reply.builder()
        // .movie(Movie.builder().mno(dto.getMno()).build())
        // .text("reply")
        // .replyer(Member.builder().mno(dto.getReplyer()).build())
        // .build();
        return replyRepository.save(dtoToEntity(dto));
    }

    // 대댓글 작성
    // 대댓글을 달 댓글이 존재하지 않으면 널 반환
    public Reply rereplyInsert(ReplyDTO dto) {
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

    // 댓글 내용 변경
    public ReplyDTO updateReply(ReplyDTO dto) {
        Reply reply = replyRepository.findById(dto.getRno()).get();
        reply.changeText(dto.getText());

        return entityToDto(replyRepository.save(reply));
    }

    public void deleteReply(Long id) {
        replyRepository.deleteById(id);
    }

    private Reply dtoToEntity(ReplyDTO dto) {
        Reply reply = null;
        if (dto.getRef() == null) {
            // 대댓글이 아니면
            reply = Reply.builder()
                    .rno(dto.getRno())
                    .text(dto.getText())
                    .replyer(Member.builder().userCode(dto.getReplyerId()).build())
                    .movie(Movie.builder().mid(dto.getMno()).build())
                    .mention(dto.getMentionId())
                    .build();
        } else if (replyRepository.findById(dto.getRef()).get().getMovie().getMid() == dto.getMno()) {
            reply = Reply.builder()
                    .rno(dto.getRno())
                    .text(dto.getText())
                    .replyer(Member.builder().userCode(dto.getReplyerId()).build())
                    .movie(Movie.builder().mid(dto.getMno()).build())
                    .mention(dto.getMentionId())
                    .ref(dto.getRef())
                    .build();
        } else {
            // 제거된 댓글에 달 때
            return null;
        }
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

        // 멘션이 있으면 추가해줌
        if (reply.getMention() != null) {
            dto.setMentionId(reply.getMention());
            dto.setMention(memberRepository.findById(reply.getMention()).get().getUserName());
        }
        return dto;
    }
}
