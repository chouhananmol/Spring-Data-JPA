package com.springproject.springdatajpa.repository;

import com.springproject.springdatajpa.Model.Guardian;
import com.springproject.springdatajpa.Model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("anmol@gmail.com")
                .firstName("Anmol")
                .lastName("chouhan")
//                .guardianEmail("guardian@gmail.com")
//                .guardianName("Guardian Namee")
//                .guardianMobile("9376865801")
                .build();
        studentRepository.save(student);
    }


    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Guardain anmol")
                .mobile("787887870")
                .email("GuardainVishal@gmail.com")
                .build();

        Student student = Student.builder()
                .firstName("Vishal")
                .lastName("Chanchlani")
                .emailId("vishal@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println("Student List = "+studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students =
                studentRepository.findByFirstName("Anmol");
        System.out.println("Students by name: "+students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students =
                studentRepository.findByFirstNameContaining("Anmol");
        System.out.println("Students by name: "+students);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students =
                studentRepository.findByGuardianName("Guardain anmol");

        System.out.println("Students by name: "+students);
    }

    @Test
     public void printStudentByEmail(){
        Student student =
                studentRepository.getStudentUsingEmail("vishal@gmail.com");

        System.out.println("Student by email : "+student);
    }

    @Test
    public void printStudentByEmailNative(){
        Student student =
                studentRepository.getStudentUsingEmailNative("vishal@gmail.com");

        System.out.println("Student by email Native: "+student);
    }

    @Test
    public void printStudentByEmailNativeNamedParam(){
        Student student =
                studentRepository.getStudentUsingEmailNativeNamedParam("vishal@gmail.com");

        System.out.println("Student by email Parameter: "+student);
    }

    @Test
    public void updateStudentUsingEmail(){
        int s = studentRepository.updateStudentNameUsingEmail("noMore_vishal","vishal@gmail.com");
//        System.out.println("S="+s);
    }


}