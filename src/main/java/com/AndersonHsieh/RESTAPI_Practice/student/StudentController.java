package com.AndersonHsieh.RESTAPI_Practice.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

//instead of http://localhost:8080, we use http://localhost:8080/api/v1/student
@RequestMapping(path = "api/v1/student")
@RestController//this is the API layer
public class StudentController {

    private final StudentService studentService;

    @Autowired
    //@Autowired is used for dependency injection, and is taken cared of by Spring framework
    private StudentController(StudentService studentService){
        this.studentService = studentService;
    }


    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void editStudent(
           @PathVariable("studentId") Long studentId,
           @RequestParam(required = false) String name,
           @RequestParam(required = false) String email){
        //@RequestParam is passed from http url in the form of:
        //http://localhost:8080/api/vl/student/<id>?name=<name>&email=<email>
        studentService.editStudent(studentId,name,email);
    }

}
