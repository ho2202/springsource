package com.example.todo.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.entity.Todo;

@SpringBootTest
public class TodoRepositoryTest {
    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testRead() {
        todoRepository.findAll().forEach(todo -> System.out.println(todo));
    }

    @Test
    public void testInsert() {
        IntStream.range(1, 10).forEach(i -> {
            Todo todo = new Todo();
            todo.setContent("내용" + i);
            todoRepository.save(todo);
        });
    }

    @Test
    public void testDelete() {
        todoRepository.deleteById(11L);
    }

    @Test
    public void testUpdate() {
        Todo todo = todoRepository.findById(3L).get();
        todo.setContent("null");
        todoRepository.save(todo);
    }

    @Test
    public void testRead2() {
        todoRepository.findByCompleted(true).forEach(todo -> System.out.println(todo));
    }

    @Test
    public void testRead3() {
        todoRepository.findByImportanted(false).forEach(todo -> System.out.println(todo));
    }
}
