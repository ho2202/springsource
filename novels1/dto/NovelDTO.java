package com.example.novels.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class NovelDTO {

    private Long id;
    private String title;
    private String author;

    private LocalDate publishedDate; // 출판일

    private boolean available; // 이용 가능

    // 장르
    private String genreName;
    private Long gid;

    private int rating;
}
