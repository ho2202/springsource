package com.example.todo.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.todo.dto.TodoDTO;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class TodoService {

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    public List<TodoDTO> list(boolean completed) {
        List<Todo> list = todoRepository.findByCompleted(completed);

        List<TodoDTO> todos = list.stream().map(todo -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());
        return todos;
        // List<TodoDTO> todos = new ArrayList<>();
        // list.forEach(todo -> {
        // TodoDTO dto = modelMapper.map(todo, TodoDTO.class);
        // todos.add(dto);
        // });
    }
}
