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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/create")
    public void getCreate() {
        log.info("ToDo 작성 폼 요청");
    }

    @PostMapping("/create") // 안됨
    public String postCreate(TodoDTO todoDTO, RedirectAttributes rttr) {
        log.info("새로운 ToDo {}", todoDTO);
        Long id = todoService.create(todoDTO);
        rttr.addAttribute("id", id);
        return "redirect:/todo/read";
    }

    @GetMapping("/remove")
    public String getMethodName(Long id) {
        log.info("삭제 {}", id);
        todoService.remove(id);
        return "redirect:/todo/list";
    }

    @GetMapping("/list")
    public void getList(@RequestParam(defaultValue = "0") boolean completed, Model model) {
        log.info("todo 가져오기 completed = {}", completed);
        List<TodoDTO> todos = todoService.list(completed);
        model.addAttribute("todos", todos);
        model.addAttribute("completed", completed);
    }

    @GetMapping("/read")
    public void getRead(Long id, Model model) {
        log.info("조회 {}", id);
        TodoDTO dto = todoService.read(id);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String postCompleted(TodoDTO dto, RedirectAttributes rttr) {
        log.info("수정 {}", dto);
        Long id = todoService.changeCompleted(dto);
        rttr.addAttribute("id", id);
        return "redirect:/todo/read";
    }

}
