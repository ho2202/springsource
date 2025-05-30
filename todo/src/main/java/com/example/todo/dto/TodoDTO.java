package com.example.todo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDTO {
    private Long id;
    private String content;
    private boolean completed;
    private boolean importanted;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
