package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.entity.Todo;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // select 문을 대신할 메서드 생성
    // 완료 목록
    List<Todo> findByCompleted(boolean completed);

    // 중요 목록
    List<Todo> findByImportanted(boolean importanted);
}
