package com.example.elastic1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDocumentDTO {

    private String id;

    private String name;

    private Long age;

    private Boolean isActive;
}
