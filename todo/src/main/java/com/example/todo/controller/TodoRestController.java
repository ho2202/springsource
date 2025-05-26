package com.example.todo.controller;

import java.util.List;

import org.springframework.ui.Model;

import com.example.todo.dto.TodoDTO;
import com.example.todo.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoRestController {
    private final TodoService todoService;

    @PostMapping("/add")
    public TodoDTO postCreate(@RequestBody TodoDTO todoDTO) {
        log.info("새로운 ToDo {}", todoDTO);
        return todoService.create2(todoDTO);
    }

    @DeleteMapping("/{id}")
    public String getRemove(@PathVariable Long id) {
        log.info("삭제 {}", id);
        todoService.remove(id);
        return "success";
    }

    @GetMapping("/")
    public List<TodoDTO> getList() {
        log.info("todos 가져오기 ");
        List<TodoDTO> todos = todoService.list2();
        return todos;
    }

    @GetMapping("/read")
    public void getRead(Long id, Model model) {
        log.info("조회 {}", id);
        TodoDTO dto = todoService.read(id);
        model.addAttribute("dto", dto);
    }

    @PutMapping("/{id}")
    public Long postCompleted(@PathVariable Long id, @RequestBody TodoDTO dto) {
        log.info("수정 {}", dto);
        Long tid = todoService.changeCompleted(dto);
        return tid;
    }

}
