package com.example.reply.service;

import org.springframework.stereotype.Service;

import com.example.reply.dto.RecommendationDTO;
import com.example.reply.entity.User;
import com.example.reply.entity.Recommendation;
import com.example.reply.entity.Reply;
import com.example.reply.repository.MemberRepository;
import com.example.reply.repository.MovieRepository;
import com.example.reply.repository.RecommendRepository;
import com.example.reply.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Service
@Log4j2
public class RecommendService {
    private final RecommendRepository recommendRepository;
    private final ReplyRepository replyRepository;
    private final MovieRepository movieRepository;
    private final MemberRepository memberRepository;

    // public void updateRecommend(RecommendationDTO dto){
    // Reply reply = replyRepository.findById(dto.getReplyId()).get();
    // Member user = memberRepository.findById(dto.getUserId()).get();
    // List<Recommendation> list = recommendRepository.findByReplyIdAndUserId(reply,
    // user);
    // if () {

    // recommendRepository.save(dto);
    // }
    // }
    public RecommendationDTO recommendReply(Long replyId, String userId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow();
        User user = memberRepository.findById(userId).orElseThrow();

        boolean alreadyRecommended = recommendRepository.existsByUserAndReply(user, reply);
        if (alreadyRecommended) {
            Recommendation rec = recommendRepository.findByReplyAndUser(reply, user).get();
            recommendRepository.delete(rec);
        }
        Recommendation rec = new Recommendation();
        rec.setUser(user);
        rec.setReply(reply);

        return entityToDto(recommendRepository.save(rec));
    }

    // public void cancelRecommendation(Long replyId, Long userId) {
    // Reply reply = replyRepository.findById(replyId).orElseThrow();
    // Member user = memberRepository.findById(userId).orElseThrow();

    // Recommendation rec = recommendRepository.findByReplyAndUser(reply, user)
    // .orElseThrow(() -> new IllegalStateException("추천 기록이 없습니다."));
    // recommendRepository.delete(rec);
    // }

    public void dtoToEntity() {
    }

    public RecommendationDTO entityToDto(Recommendation rec) {
        RecommendationDTO recDto = RecommendationDTO.builder()
                .id(rec.getId())
                .userId(rec.getUser().getUserCode())
                .replyId(rec.getReply().getRno())
                .build();

        return recDto;
    }
}
