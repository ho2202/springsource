package com.example.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReplyDTO {
    private Long rno;

    private String text;
    private String replyer;

    private Long bno;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
