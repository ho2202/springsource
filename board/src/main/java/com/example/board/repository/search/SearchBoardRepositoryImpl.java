package com.example.board.repository.search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.board.entity.Board;
import com.example.board.entity.QBoard;
import com.example.board.entity.QMember;
import com.example.board.entity.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Page<Object[]> list(String type, String keyword, Pageable pageable) {
        log.info("SearchBoard");
        QMember member = QMember.member;
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        // FROM BOARDTBL b JOIN BOARD_MEMBER bm ON b.MEMBER_ID = bm.EMAIL;
        JPQLQuery<Board> query = from(board);
        query.leftJoin(member).on(board.member.eq(member));

        // ( SELECT count(r.BOARD_ID) FROM REPLY r WHERE r.BOARD_ID = b.bno GROUP BY
        // r.BOARD_ID )
        JPQLQuery<Long> replyCount = JPAExpressions.select(reply.rno.count())
                .from(reply)
                // WHERE r.BOARD_ID = b.bno
                .where(reply.board.eq(board))
                // GROUP BY r.BOARD_ID) AS reply_cnt, b.CREATED_DATE, bm.NAME
                .groupBy(reply.board);

        JPQLQuery<Tuple> tuple = query.select(board, member, replyCount);
        log.info("=======");

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(board.bno.gt(0L));
        if (type != null) {

            BooleanBuilder builder = new BooleanBuilder();

            if (type.contains("t")) {
                builder.or(board.title.contains(keyword));
            }
            if (type.contains("c")) {
                builder.or(board.content.contains(keyword));
            }
            if (type.contains("w")) {
                builder.or(board.member.name.contains(keyword));
            }
            booleanBuilder.and(builder);
        }
        tuple.where(booleanBuilder);

        // Sort
        Sort sort = pageable.getSort();
        // sort 기준이 여럿일 수 있어서
        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;

            String prop = order.getProperty();
            PathBuilder<Board> orderBuilder = new PathBuilder<>(Board.class, "board");
            tuple.orderBy(new OrderSpecifier(direction, orderBuilder.get(prop)));
        });
        // 전체 리스트 조회 + sort 적용
        // 페이지 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();
        long count = tuple.fetchCount();
        List<Object[]> list = result.stream().map(t -> t.toArray()).collect(Collectors.toList());

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Object[] getBoardByBno(Long bno) {
        log.info("ReadBoard");
        QMember member = QMember.member;
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(member).on(board.member.eq(member));
        query.where(board.bno.eq(bno));

        JPQLQuery<Long> replyCount = JPAExpressions.select(reply.rno.count())
                .from(reply)
                .where(reply.board.eq(board))
                .groupBy(reply.board);

        JPQLQuery<Tuple> tuple = query.select(board, member, replyCount);
        Tuple row = tuple.fetchFirst();
        return row.toArray();
    }

}
