package com.AndersonHsieh.RESTAPI_Practice.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository//this is the data access layer
public interface StudentRepository
        extends JpaRepository<Student,Long> {

    //SELECT * FROM student WHERE email = 'email passed in'
    @Query("SELECT s FROM Student s WHERE s.email = ?1")//this is JPQL
    Optional<Student> findStudentByEmail(String email);
}
