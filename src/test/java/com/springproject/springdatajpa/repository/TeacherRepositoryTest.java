package com.springproject.springdatajpa.repository;

import com.springproject.springdatajpa.Model.Course;
import com.springproject.springdatajpa.Model.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){
        Course course1 = Course.builder()
                .title("DBA")
                .credit(5)
                .build();

        Course course2 = Course.builder()
                .title("CDA")
                .credit(12)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Avinash")
                .lastName("Sirji")
//                .courses(List.of(course1, course2))  //when O2M with teacher
                .build();

        teacherRepository.save(teacher);
    }

}