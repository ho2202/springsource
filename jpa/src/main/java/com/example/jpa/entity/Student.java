package com.example.jpa.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@EntityListeners(value = AuditingEntityListener.class)
@Table(name = "studenttbl")
@Entity // table과 연동
public class Student {
    @Id // primary key
    @SequenceGenerator(name = "student_seq_gen", sequenceName = "student_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq_gen")
    private Long id; // number(19,0)
    // @Column(name = "sname", nullable = false, unique = true)
    // @Column(columnDefinition = "sname, varchar2(10) not null unique")
    @Column(length = 20, nullable = false)
    private String name;

    // @Column(columnDefinition = "number(8,0)")
    // private int grade;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Grade grade;

    @Column(columnDefinition = "varchar(1) constraint chk_gender check (gender in ('M','F'))")
    private String gender; // GENDER

    @CreationTimestamp
    private LocalDateTime cDateTime; // C_DATE_TIME

    @UpdateTimestamp
    private LocalDateTime uDateTime;// U_DATE_TIME

    @CreatedDate
    private LocalDateTime cDateTime2;
    @LastModifiedDate
    private LocalDateTime uDateTime2;

    // enum 정의
    public enum Grade {
        FRESHMAN, SOPHOMORE, JUNIOR, SENIOR
    }
}
