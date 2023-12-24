package com.springproject.springdatajpa.repository;

import com.springproject.springdatajpa.Model.Course;
import com.springproject.springdatajpa.Model.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class courseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    @Test
    public void saveCourseMaterial(){
        Course course = Course.builder()
                .title("CD1")
                .credit(3)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .course(course) // Need to save course before passing here using cascading(in model)
                .url("www.indf.com")
                .build();

        repository.save(courseMaterial);
        /* Both will execute
        insert into course (credit,title,course_id) values (?,?,?)
        insert into course_material (course_id,url,course_material_id) values (?,?,?)
         */
    }

    @Test
    public void printAllCourseMaterials(){
        List<CourseMaterial> courseMaterials = repository.findAll();
        System.out.println("Course Materials: "+ courseMaterials);
    }


}