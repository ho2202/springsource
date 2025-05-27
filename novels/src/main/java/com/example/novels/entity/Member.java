package com.example.novels.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@ToString
public class Member {

    @Id
    private String email;

    private String pw;

    private String nickname;

    private boolean social;
}
