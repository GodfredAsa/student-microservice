package io.turntabl.studentmicroservice.controller;


import io.turntabl.studentmicroservice.entity.Student;
import io.turntabl.studentmicroservice.service.Implementation.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000/")
@RequestMapping( path = "/api/student")
@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceImpl service;

    @GetMapping("/")
    public List<Student> allStudents(){
        return service.getAllStudents();
    }

    @PostMapping("/")
    public Student saveStudent(@RequestBody Student student){
        return service.createStudent(student);
    }

// the update method creates the user if the user not found
    @PutMapping("student/{id}")
    public Student editStudentRecords(@RequestBody Student student, @PathVariable Long id){
      return service.updateStudent(student, id);
    }

    @GetMapping("student/{id}")
    Student getStudentById(@PathVariable Long id){
        return service.getStudentById(id);
    }

    @DeleteMapping("/student/{id}")
    public void removeStudent(@PathVariable Long id){
        service.deleteStudentById(id);
    }

    @GetMapping("/student/number")
    public Long getNumberOfStudent(){
        return service.getNumberOfStudents();
    }

//    find by email streams controller
    @RequestMapping(value = "student/students/{email}",method = RequestMethod.GET)
    public Student findStudentByEmail(@PathVariable String email){
        return service.findByEmail(email);
    }

    @GetMapping("/students/{email}")
    public Student getStudentByEmail(@PathVariable("email") String email){
        return service.getStudentByEmail(email);
    }

}
