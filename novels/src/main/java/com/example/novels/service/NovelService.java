package com.example.novels.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.novels.dto.NovelDTO;
import com.example.novels.dto.PageRequestDTO;
import com.example.novels.dto.PageResultDTO;
import com.example.novels.entity.Genre;
import com.example.novels.entity.Novel;
import com.example.novels.repository.GradeRepository;
import com.example.novels.repository.NovelRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class NovelService {

    private final GradeRepository gradeRepository;
    private final NovelRepository novelRepository;

    public Long avaUpdate(NovelDTO novelDTO) {
        Novel novel = novelRepository.findById(novelDTO.getId()).get();
        novel.changeAvailable(novelDTO.isAvailable());
        return novelRepository.save(novel).getId();
    }

    public void novelRemove(Long id) {
        gradeRepository.deleteByNovel(Novel.builder().id(id).build());
        novelRepository.deleteById(id);
    }

    public Long novelInsert(NovelDTO novelDTO) {
        Novel novel = Novel.builder()
                .title(novelDTO.getTitle())
                .author(novelDTO.getAuthor())
                .genre(Genre.builder().id(novelDTO.getGid()).build())
                .publishedDate(novelDTO.getPublishedDate())
                .available(novelDTO.isAvailable())
                .build();
        return novelRepository.save(novel).getId();
    }

    public PageResultDTO<NovelDTO> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
                Sort.by("id").descending());
        Page<Object[]> result = novelRepository.list(pageable);

        List<NovelDTO> dtoList = result.get().map(arr -> {
            Novel novel = (Novel) arr[0];
            Genre genre = (Genre) arr[1];
            Double rating = (Double) arr[2];

            NovelDTO novelDTO = entityToDto(novel, genre, rating);
            return novelDTO;

        }).collect(Collectors.toList());
        long totalCnt = result.getTotalElements();

        return PageResultDTO.<NovelDTO>withAll().dtoList(dtoList).totalCount(totalCnt).pageRequestDTO(pageRequestDTO)
                .build();
    }

    public NovelDTO getRow(Long id) {
        Object[] result = novelRepository.getNovelById(id);
        Novel novel = (Novel) result[0];
        Genre genre = (Genre) result[1];
        Double rating = (Double) result[2];

        NovelDTO novelDTO = entityToDto(novel, genre, rating);

        return novelDTO;
    }

    private NovelDTO entityToDto(Novel novel, Genre genre, Double rating) {
        NovelDTO novelDTO = NovelDTO.builder()
                .id(novel.getId())
                .title(novel.getTitle())
                .author(novel.getAuthor())
                .publishedDate(novel.getPublishedDate())
                .gid(novel.getId())
                .available(novel.isAvailable())
                .genreName(genre.getName())
                .rating(rating != null ? rating.intValue() : 0)
                .build();
        return novelDTO;
    }
}
