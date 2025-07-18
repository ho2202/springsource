package com.example.elastic1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.elastic1.dto.UserDocumentDTO;
import com.example.elastic1.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public List<UserDocumentDTO> getList() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public UserDocumentDTO getRow(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public UserDocumentDTO putRow(@PathVariable String id, @RequestBody UserDocumentDTO userDocumentDTO) {
        userDocumentDTO.setId(id);
        return userService.updateUser(userDocumentDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteRow(@PathVariable String id) {
        userService.removeUser(id);
        return id;
    }

}
