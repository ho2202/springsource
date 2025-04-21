package com.example.relation.repositiory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.relation.entity.cascade.Child;
import com.example.relation.entity.cascade.Parent;
import com.example.relation.repository.ChildRepository;
import com.example.relation.repository.ParentRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ParentRepositoryTest {
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private ChildRepository childRepository;

    @Test
    public void testInsert() {
        Parent parent = new Parent();
        parent.setName("parent1");
        parentRepository.save(parent);

        Child child = new Child();
        child.setName("child1");
        child.setParent(parent);
        childRepository.save(child);

    }

    @Test
    public void testInsert2() {
        Parent parent = new Parent();
        parent.setName("parent2");
        // 영향 없음
        parent.getChilds().add(Child.builder().name("홍길동").parent(parent).build());
        parent.getChilds().add(Child.builder().name("성춘향").parent(parent).build());
        parent.getChilds().add(Child.builder().name("박보검").parent(parent).build());

        parentRepository.save(parent);
    }

    @Test
    public void testDelete() {
        parentRepository.deleteById(1L);
    }

    @Test
    @Transactional
    public void testDelete2() {
        Parent parent = parentRepository.findById(1L).get();
        parent.getChilds().remove(0);
        System.out.println(parent.getChilds());
        parentRepository.save(parent);
    }
}
