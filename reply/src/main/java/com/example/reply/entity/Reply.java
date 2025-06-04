package com.example.reply.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString(exclude = { "movie", "replyer" })
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TReply")

public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @Column(nullable = false)
    private String text;

    @Builder.Default
    // 댓글의 추천수
    private int recommend = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid")
    private Movie movie;

    // 대상 댓글의 rno
    private Long ref;

    // 대상의 유저 아이디
    private Long mention;

    public void changeText(String text) {
        this.text = text;
    }
}
