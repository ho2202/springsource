package com.example.jpa.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@EntityListeners(value = AuditingEntityListener.class)

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no; // 자동

    @Column(unique = true)
    private String userid; // unique
    private String name;
    private int age;

    @Enumerated(EnumType.STRING)
    private RoleType type; // admin, user

    @CreatedDate
    private LocalDateTime regDate;

    @Column(length = 2000)
    private String description;

    public enum RoleType {
        ADMIN, USER
    }
}