package com.example.reply.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ReplyDTO {
    private Long rno;
    private String text;
    // 댓글 작성자
    private String replyerId;
    private Long replyer;

    private Long mno;
    private int recommend;

    private Long ref;
    // 대댓글 대상 댓글 작성자 이름
    private String mention;
    private Long mentionId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;
}
