package com.example.jpa.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.dto.MemoDTO;
import com.example.jpa.entity.Memo;

@SpringBootTest
public class MemoServiceTest {
    @Autowired
    private MemoService memoService;

    @Test
    public void getListTest() {
        List<MemoDTO> list = memoService.getList();
        list.forEach(dto -> System.out.println(dto));
    }

    @Test
    public void getRowTest() {
        MemoDTO dto = memoService.getRow(2L);
        System.out.println(dto);
    }

    @Test
    public void memoUpdateTest() {
        MemoDTO dto = MemoDTO.builder().mno(12L).memoText("수정 텍스트").build();
        Long mno = memoService.memoUpdate(dto);
        System.out.println("수정된 메모 번호 : " + mno);
    }

    @Test
    public void memoCreateTest() {
        MemoDTO dto = MemoDTO.builder().memoText("생성된 메모 텍스트").build();
        System.out.println("추가된 메모 번호 : " + memoService.memoCreate(dto));
    }

    @Test
    public void memoDeleteTest() {
        memoService.memoDelete(3L);
        System.out.println("데이터 삭제");
    }
}
