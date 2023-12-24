package com.springproject.springdatajpa.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString(exclude = "course")
public class CourseMaterial { // O2O with Course and vice versa


    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    //Since material class cannot exist without course class we need o2o mapping
    @OneToOne(
            cascade = CascadeType.ALL, // this will save course tuple too when saving material if not already exist , pass permission to child
            fetch = FetchType.LAZY, // exclude course data
            optional = false  // This will make it required that when saving course , course material is required
    )
    @JoinColumn(    // setting name of col foreign key id referring to
            name = "course_id",         //sql name
            referencedColumnName = "courseId" //java class name
    )
    private Course course;

}