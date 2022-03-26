package io.turntabl.studentmicroservice.service.Implementation;

import io.turntabl.studentmicroservice.entity.Student;
import io.turntabl.studentmicroservice.repository.StudentRepo;
import io.turntabl.studentmicroservice.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;



@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private final StudentRepo repository;


    @Override
    public Student createStudent(@RequestBody Student student){
        return repository.save(student);
    }


    @Override
    public List<Student> getAllStudents(){
        return repository.findAll();
    }


// updating a student
    @Override
    public Student updateStudent(@RequestBody Student student, @PathVariable Long id){
        return repository.findById(id)
                .map(updateStudent -> {
                    updateStudent.setFirstName(student.getFirstName());
                    updateStudent.setLastName(student.getLastName());
                    updateStudent.setEmail(student.getEmail());
                    return repository.save(updateStudent);
                })
                .orElseGet(() -> {
                    student.setId(id);
                    return repository.save(student);
                });


    }


    //getting student by id
    public Student getStudentById(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(IllegalStateException::new);
    }


    // getting a student by email
    @Override
    public Student getStudentByEmail(String email){
        return repository.findByEmail(email);
    }

// using streams to find student by email
    public Student findByEmail(@PathVariable String email){
       return repository.findAll()
               .stream()
               .distinct()
               .filter( student -> student.getEmail().equals(email))
               .findAny().orElseThrow();
    }




    // deleting a student
    @Override
    public void deleteStudentById(Long id){
         repository.deleteById(id);
    }


//  finding the number of students
    @Override
    public Long getNumberOfStudents(){
       return repository.count();
    }



}
