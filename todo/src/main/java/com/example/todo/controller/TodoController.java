package com.example.todo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.todo.dto.TodoDTO;
import com.example.todo.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/list")
    public void getList(boolean completed, Model model) {
        log.info("미완료 todo 가져오기 {}", completed);
        List<TodoDTO> todos = todoService.list(completed);
        model.addAttribute("todos", todos);
    }

}
