package com.example.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {

    private Long bno;
    // board
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    // member
    private String email;
    private String name;

    private Long replyCount;
}
