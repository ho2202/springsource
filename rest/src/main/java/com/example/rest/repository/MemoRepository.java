package com.example.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rest.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    // // where mno<5
    // List<Memo> findByMNoLessThan(Long mno);

    // // where mno <10 order by mno desc
    // List<Memo> findByMnoLessThanOrderByMnoDesc(Long mno);

    // List<Memo> findByMemoTextContaning(String memoText);

}
