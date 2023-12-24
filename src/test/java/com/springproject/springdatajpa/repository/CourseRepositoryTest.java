package com.springproject.springdatajpa.repository;

import com.springproject.springdatajpa.Model.Course;
import com.springproject.springdatajpa.Model.Guardian;
import com.springproject.springdatajpa.Model.Student;
import com.springproject.springdatajpa.Model.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void saveCourse(){
        Course course = Course.builder()
                .title("DSA")
                .credit(3)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void printAllCourses(){
        List<Course> courses = courseRepository.findAll();
        System.out.println("Courses :"+ courses);
    }


    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Rahul")
                .lastName("Patio")
                .build();

        Course course = Course.builder()
                .title("Udemy")
                .credit(11)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void saveCourseWithTeacherAndStudent(){
        Teacher teacher = Teacher.builder()
                .firstName("nijat")
                .lastName("sir")
                .build();

        Student student = Student.builder()
                .firstName("prertu")
                .lastName("kimar")
                .emailId("pertuu@gmail.com")
                .build();

//        Student student2 = Student.builder()
//                .firstName("Adarsg")
//                .lastName("okijj")
//                .emailId("odiilS@gmail.com")
//                .build();

        Course course = Course.builder()
                .title("Kinn")
                .credit(1)
                .teacher(teacher)
                .build();

        course.addStudent(student);
//        course.addStudent(student2);

        courseRepository.save(course);
    }

    //Pagination
    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);

        List<Course> courses = courseRepository.findAll(secondPageWithTwoRecords).getContent();

        long totalElements = courseRepository.findAll(secondPageWithTwoRecords).getTotalElements();

        long totalPages = courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();

        System.out.println("Total Elements:-"+ totalElements);

        System.out.println("Total Pages:-"+ totalPages);

        System.out.println("Courses ="+ courses);
    }

    //Pagination with Sorting
    @Test
    public void findAllSorting(){
        Pageable sortByTitle = PageRequest.of(0, 4, Sort.by("title"));

        Pageable sortByCreditDesc = PageRequest.of(0, 3, Sort.by("credit").descending());

        Pageable sortByTitleAndCreditDesc = PageRequest.of(0, 5, Sort.by("title").descending().and(Sort.by("credit")));

        List<Course> courseswithtitle = courseRepository.findAll(sortByTitle).getContent();
        List<Course> courseswithcredit = courseRepository.findAll(sortByCreditDesc).stream().toList();
        List<Course> courseswithtitleandcredit = courseRepository.findAll(sortByTitleAndCreditDesc).getContent();

        System.out.println("Course by title - "+ courseswithtitle);
        System.out.println("Course by credit - "+ courseswithcredit);
        System.out.println("Course by title and credit - "+ courseswithtitleandcredit);

    }

    //Custom sorting method Testing
    @Test
    public void printFindByTitleContaining(){
        Pageable firstPageTenRecords = PageRequest.of(0, 10);

        List<Course> coursesContainingTitle = courseRepository.findByTitleContaining("CD", firstPageTenRecords).getContent();

        System.out.println("Courses- "+ coursesContainingTitle);
    }

}