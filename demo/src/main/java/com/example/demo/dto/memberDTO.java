package com.example.demo.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Length(min = 2, max = 10)
    private String name;
    private String userid;
    @NotBlank(message = "비밀번호를 입력")
    private String password;

    private boolean check;

    @NotNull
    @Min(value = 0, message = "최소 0 이상")
    @Max(value = 100, message = "나이는 100 이하")
    private Integer age;
}
