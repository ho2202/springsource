package com.example.movie.dto;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {
    private Long rno;
    private int grade;
    private String text;
    // member
    private Long mid;
    private String email;
    private String nickname;

    private Long mno;
    // date
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
