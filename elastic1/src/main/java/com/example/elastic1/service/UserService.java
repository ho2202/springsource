package com.example.elastic1.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.elastic1.document.UserDocument;
import com.example.elastic1.dto.UserDocumentDTO;
import com.example.elastic1.repository.UserDocumentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDocumentRepository userDocumentRepository;

    public UserDocumentDTO getUser(String id) {
        return docToDto(userDocumentRepository.findById(id).orElseThrow(() -> new RuntimeException("없는 사용자")));
    }

    public String removeUser(String id) {
        userDocumentRepository.deleteById(id);
        return id;
    }

    public UserDocumentDTO create(UserDocumentDTO userDocumentDTO) {
        UserDocument userDocument = dtoToDoc(userDocumentDTO);
        UserDocument savDocument = userDocumentRepository.save(userDocument);
        return docToDto(savDocument);
    }

    public List<UserDocumentDTO> list() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<UserDocument> result = userDocumentRepository.findAll(pageable);
        return result.get().map(doc -> docToDto(doc)).collect(Collectors.toList());
    }

    public UserDocumentDTO updateUser(UserDocumentDTO userDocumentDTO) {
        UserDocument doc = userDocumentRepository.findById(userDocumentDTO.getId())
                .orElseThrow(() -> new RuntimeException("없는 사용자"));
        doc.setAge(userDocumentDTO.getAge());
        doc.setId(userDocumentDTO.getId());
        doc.setIsActive(userDocumentDTO.getIsActive());
        return docToDto(userDocumentRepository.save(doc));
    }

    private UserDocument dtoToDoc(UserDocumentDTO userDocumentDTO) {
        return UserDocument.builder()
                .id(userDocumentDTO.getId())
                .name(userDocumentDTO.getName())
                .age(userDocumentDTO.getAge())
                .isActive(userDocumentDTO.getIsActive())
                .build();
    }

    private UserDocumentDTO docToDto(UserDocument doc) {
        return UserDocumentDTO.builder()
                .age(doc.getAge())
                .isActive(doc.getIsActive())
                .name(doc.getName())
                .id(doc.getId())
                .build();
    }
}
