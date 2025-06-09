package com.example.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RecommendationDTO {
    private Long id;
    private Long userId;
    private Long replyId;
}
