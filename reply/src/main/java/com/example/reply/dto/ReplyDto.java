package com.example.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ReplyDto {
    private Long rno;
    private String text;
    private int recommend;
    private Long replyer;
    private Long mno;
    private Long ref;
    private Long mention;
}
