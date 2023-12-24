package com.springproject.springdatajpa.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Course {
    /*
    * O2O with Course Material - One Course will have One CourseMaterial
    * M2O with Teacher - Many courses can be taught by One Teacher
    * M2M with Student - Many courses can be studied by Many students
     */

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    private String title;
    private Integer credit;

    @OneToOne(
            mappedBy = "course"  // Already mapped in material class - field name
    ) // this will also return course material info when printing course
    private CourseMaterial courseMaterial;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;


    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(         // This will create new table - student_course_map with student_id and course_id
            name = "student_course_map",
            joinColumns = @JoinColumn(      // Column from course table
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(   // Column from student table
                    name = "student_id",
                    referencedColumnName = "studentId"
            )
    )
    private List<Student> students;

    public void addStudent(Student student){
        if(students == null) students = new ArrayList<>();
        students.add(student);
    }
}
