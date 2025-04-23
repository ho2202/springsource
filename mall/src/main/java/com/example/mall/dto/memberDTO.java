package com.example.mall.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class memberDTO {
    @Id
    private String id;
    @NotBlank(message = "이름을 입력")
    @Length(min = 2, max = 12)
    private String name;
    @NotBlank(message = "비밀번호를 입력")
    private String password;

    private boolean check;

    private Integer rating;
}
