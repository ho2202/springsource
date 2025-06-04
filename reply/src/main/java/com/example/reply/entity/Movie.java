package com.example.reply.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString(exclude = { "replies" })
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TMovie")

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String title;
    @Builder.Default
    @OneToMany(mappedBy = "movie") // 관계 주인은 reply, movie는 필요할 때만
    private List<Reply> replies = new ArrayList<>();
}
