package com.springproject.springdatajpa.repository;

import com.springproject.springdatajpa.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    /*
    * Read methods
    */
    List<Student> findByFirstName(String name);

    List<Student> findByFirstNameContaining(String name);  // char matched like operator

    List<Student> findByLastNameNotNull(); // all records with not null lastname field

    List<Student> findByGuardianName(String name);

    Student findByFirstNameAndLastName(String firstname, String lastname);

    @Query("select s from Student s where s.emailId = ?1")  //emailId name as in Student Model
    Student getStudentUsingEmail(String emailId); // user created method, emailId should be same as in Model


    //Java Persistant Query JPQL
    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentNameUsingEmail(String emailId);

    //Sql Native Query
    @Query(
            value = "select * FROM student s where s.email_address = ?1", // same as sql query which we will write in sql console
            nativeQuery = true
    )
    Student getStudentUsingEmailNative(String emailId);

    //Sql Native Param Query
    @Query(
            value = "select * FROM student s where s.email_address = :emailId", // using parameter from method below, we can also use mutli params too
            nativeQuery = true
    )
    Student getStudentUsingEmailNativeNamedParam(@Param("emailId") String emailId);

    /*
     * Update methods
     */
    @Modifying
    @Transactional //to rollback or commit on completion since this queries will modify data
    @Query(
            value = "update student set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameUsingEmail(String firstName, String emailId);

}
