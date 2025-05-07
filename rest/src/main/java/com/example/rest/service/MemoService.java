package com.example.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.rest.dto.MemoDTO;
import com.example.rest.entity.Memo;
import com.example.rest.repository.MemoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class MemoService {
    // Repository 호출 후 결과 받기
    private final MemoRepository memoRepository;
    private final ModelMapper modelMapper;

    public List<MemoDTO> getList() {
        List<Memo> list = memoRepository.findAll();
        List<MemoDTO> dtoList = list.stream().map(memo -> modelMapper.map(memo, MemoDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    public MemoDTO getRow(Long mno) {
        Memo memo = memoRepository.findById(mno).orElseThrow(EntityNotFoundException::new);
        // MemoDTO dto = entityToDto(memo);
        MemoDTO dto = modelMapper.map(memo, MemoDTO.class);
        return dto;

    }

    public Long memoUpdate(MemoDTO dto) {
        Memo memo = memoRepository.findById(dto.getMno())
                .orElseThrow(EntityNotFoundException::new);
        memo.changeMemoText(dto.getMemoText());
        memo = memoRepository.save(memo);
        return memo.getMno();

    }

    public void memoDelete(Long mno) {
        memoRepository.deleteById(mno);
    }

    public Long memoCreate(MemoDTO dto) {
        Memo memo = dtoToEntity(dto);
        memo = memoRepository.save(memo);
        return memo.getMno();
    }

    private Memo dtoToEntity(MemoDTO memoDto) {
        Memo memo = Memo.builder()
                .mno(memoDto.getMno())
                .memoText(memoDto.getMemoText())
                .build();
        return memo;
    }

    private MemoDTO entityToDto(Memo memo) {
        MemoDTO dto = MemoDTO.builder()
                .mno(memo.getMno())
                .memoText(memo.getMemoText())
                .createdDate(memo.getCreatedDate())
                .updatedDate(memo.getUpdatedDate())
                .build();
        return dto;
    }

}
