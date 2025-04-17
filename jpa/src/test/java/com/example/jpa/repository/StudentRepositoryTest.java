package com.example.jpa.repository;

import java.util.Optional;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Student;
import com.example.jpa.entity.Student.Grade;

@SpringBootTest // 테스트용임
public class StudentRepositoryTest {
    @Autowired // 만들어진 객체를 주입해라 (= new 클래스())
    private StudentRepository studentRepository;

    // CRUD 테스트
    // studentRepository, entity가 잘 작성 되었는지 확인
    // insert: save(Entity), select: , update: save(Entity)
    @Test
    public void insertTest() {
        LongStream.range(1, 11).forEach(i -> {

            Student student = Student.builder()
                    .name("홍길동" + i)
                    .grade(Grade.JUNIOR).gender("M").build();
            studentRepository.save(student);
        });
    }

    @Test
    public void updateTest() {
        Student student = studentRepository.findById(1L).get();
        student.setGrade(Grade.SENIOR);
        // 업데이트로 쓰임
        studentRepository.save(student);
    }

    @Test
    public void selectOneTest() {
        Optional<Student> student = studentRepository.findById(1L);
        if (student.isPresent()) {
            System.out.println(student.get());
        }
        // Student student = studentRepository.findById(1L).get();
        // System.out.println(student);
        // Student student =
        // studentRepository.findById(1L).orElseThrow(EntityNotFoundException::new);
        // System.out.println(student);
    }

    @Test
    public void selectTest() {
        // List<Student> list = studentRepository.findAll();

        // for (Student student : list) {
        // System.out.println(student);
        // }
        studentRepository.findAll().forEach(student -> System.out.println(student));
    }

    @Test
    public void deleteTest() {
        // Student student = studentRepository.findById(2L).get();
        // studentRepository.delete(student);
        studentRepository.deleteById(10L);
    }
}
