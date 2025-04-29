package com.example.book.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;

@Data
public class PageResultDTO<E> {

    private List<E> dtoList;
    // 페이지 당 글 개수
    private List<Integer> pageNumList;
    private PageRequestDTO pageRequestDTO;

    private boolean prev, next;
    private int totalCount, prevPage, nextPage, totalPage, current;

    // 빌더 이름 지정
    @Builder(builderMethodName = "withAll")
    public PageResultDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long totalCount) {
        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = (int) totalCount;
        int end = (int) (Math.ceil(pageRequestDTO.getPage() / 10.0) * 10);
        int start = end - 9;
        int last = (int) (Math.ceil(totalCount / (double) pageRequestDTO.getPage()));
        end = end > last ? last : end;
        this.prev = start > 1;
        this.next = totalCount > end * pageRequestDTO.getSize();
        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

        if (prev) {
            this.prevPage = start - 1;
        }
        if (next) {
            this.nextPage = end + 1;
        }
        totalPage = this.pageNumList.size();
        this.current = pageRequestDTO.getPage();
    }
}
