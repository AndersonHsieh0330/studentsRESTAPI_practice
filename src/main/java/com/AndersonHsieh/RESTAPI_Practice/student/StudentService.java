package com.AndersonHsieh.RESTAPI_Practice.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service//this is the service layer, implement business logic here

public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    //@Autowired means
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
        //findAll() is a function of the JpaRepository
        //returns a list like:

    }

    public void addNewStudent(Student student) {
        System.out.println(student);
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email Taken");
            //doesn't matter what exception is thrown
            //as long as an exception thrown, the api return a 500 Internal Server Error
        }
        studentRepository.save(student);
        //.save(Entity) is a method of the JpaRepository
    }

    public void deleteStudent(Long studentId) {
    boolean exists = studentRepository.existsById(studentId);
    if(!exists){
        throw new IllegalStateException(
                "student with id "+ studentId + " does not exists"
        );
        //doesn't matter what exception is thrown
        //as long as an exception thrown, the api return a 500 Internal Server Error
    }
    studentRepository.deleteById(studentId);
    }

    @Transactional
    //doesn't uses a query(a method like save(Entity))from the repository class
    //because the entity goes into a management state
    public void editStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException(
                "student with id "+ studentId + " does not exist."
        ));

        if(name != null&&
                name.length()>0 &&
                !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email != null &&
            email.length() >0 &&
            !Objects.equals(student.getEmail(),email)){
            //check whether email is taken by other student
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }

    }
}
