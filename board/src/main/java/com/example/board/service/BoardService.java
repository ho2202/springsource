package com.example.board.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResultDTO;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.ReplyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardService {
        private final BoardRepository boardRepository;
        private final ReplyRepository replyRepository;

        public Long create(BoardDTO dto) {
                Board board = dtoToEntity(dto);
                Board newBoard = boardRepository.save(board);
                return newBoard.getBno();
        }

        @Transactional // 여러개의 테이블을 동시에 수정해야 하기에 실패하면 함께 롤백
        public void delete(Long bno) {
                // 연관관계 데이터 처리
                // SQL : 댓글 삭제 후 게시글 삭제 || 댓글 부모=null, 게시글 삭제
                replyRepository.deleteByBoardBno(bno); // 댓글 삭제
                boardRepository.deleteById(bno); // 게시글 삭제
        }

        public Long update(BoardDTO dto) {
                Board board = boardRepository.findById(dto.getBno()).orElseThrow();
                board.changeTitle(dto.getTitle());
                board.changeContent(dto.getContent());
                boardRepository.save(board);
                return board.getBno();
        }

        public BoardDTO getRow(Long bno) {
                Object[] entity = boardRepository.getBoardByBno(bno);
                return entityToDto((Board) entity[0], (Member) entity[1],
                                (Long) entity[2]);
        }

        public PageResultDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {
                Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
                                Sort.by("bno").descending());
                Page<Object[]> result = boardRepository.list(pageRequestDTO.getType(), pageRequestDTO.getKeyword(),
                                pageable);

                Function<Object[], BoardDTO> fn = (entity -> entityToDto((Board) entity[0], (Member) entity[1],
                                (Long) entity[2]));
                List<BoardDTO> dtoList = result.stream().map(fn).collect(Collectors.toList());
                Long totalCount = result.getTotalElements();

                PageResultDTO<BoardDTO> pageResultDTO = PageResultDTO.<BoardDTO>withAll().dtoList(dtoList)
                                .totalCount(totalCount).pageRequestDTO(pageRequestDTO).build();
                return pageResultDTO;
        }

        private BoardDTO entityToDto(Board board, Member member, Long replyCount) {
                BoardDTO dto = BoardDTO.builder()
                                .bno(board.getBno())
                                .title(board.getTitle())
                                .content(board.getContent())
                                .createdDate(board.getCreatedDate())
                                .email(member.getEmail())
                                .name(member.getName())
                                .replyCount(replyCount)
                                .build();
                return dto;
        }

        private Board dtoToEntity(BoardDTO boardDTO) {
                Board board = Board.builder()
                                .title(boardDTO.getTitle())
                                .content(boardDTO.getContent())
                                .member(Member.builder().email(boardDTO.getEmail()).build())
                                .build();
                return board;
        }
}
