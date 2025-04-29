package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

// 상속 관계에서 안전하게 사용, 서브 클래스가 상속할 때 부모 필드도 빌더로 생성 가능
@SuperBuilder

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageRequestDTO {
    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size = 10;

    private String type;
    private String keyword;

}
