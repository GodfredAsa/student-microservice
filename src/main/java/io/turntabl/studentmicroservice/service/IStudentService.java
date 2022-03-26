package io.turntabl.studentmicroservice.service;

import io.turntabl.studentmicroservice.entity.Student;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

public interface IStudentService {

//    create or save a student
    Student createStudent(Student student);

//    get all students
    List<Student> getAllStudents();

    // updating a  student
    Student updateStudent(@RequestBody Student student, @PathVariable Long id);

//    gets a student using the id
    Student getStudentById(Long id);

//    gets a student using the email
    Student getStudentByEmail(String email);

//    delete student using the id
    void deleteStudentById(Long id);


    //  finding the number of students
    Long getNumberOfStudents();
}
