package io.turntabl.studentmicroservice.service.Implementation;

import io.turntabl.studentmicroservice.entity.Student;
import io.turntabl.studentmicroservice.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StudentServiceImpl.class})
@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    Student student;

    @Mock private StudentRepo repository;
    private StudentServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentServiceImpl(repository);
        student = mock(Student.class);
    }


    @Test
    void shouldCreateStudent() {
        Student.builder()
                .firstName("Stephen")
                .lastName("Bentil Mensah")
                .email("bentil.mensah@gmail.com")
                .build();
//        underTest.createStudent(student);
        when(repository.save(student)).thenReturn(student);
        Student result = underTest.createStudent(student);
        Assertions.assertThat(result).isEqualTo(student);

    }


    @Test
    void shouldGetAllStudents() {
        List<Student> allStudents = Arrays.asList(
                new Student(1L,"Ama","Babs", "a@c.com"),
                new Student(2L,"Ama","Daniels", "amadan@c.com"));
        when(repository.findAll()).thenReturn(allStudents);
        List<Student> result = underTest.getAllStudents();
        System.out.println(result);
        Assertions.assertThat(result).isEqualTo(allStudents);
    }


    @Test
    void shouldUpdateStudent() {
//        Student student = mock(Student.class);
        when(repository.findById(this.student.getId())).thenReturn(java.util.Optional.of(student));
        Student result = underTest.updateStudent(student, student.getId());
    }


    @Test
    void shouldGetStudentById() {
        Student student = new Student(1L,"Ama","Babs", "a@c.com");
        when(repository.findById(student.getId())).thenReturn(java.util.Optional.of(student));
        Student result = underTest.getStudentById(student.getId());
        System.out.println(result);
        assertThat(result);
    }


    @Test
    void shouldGetStudentByEmail() {
        Student student = new Student(1L,"Ama","Babs", "fred@gmail.com");
        when(repository.findByEmail(student.getEmail())).thenReturn(student);
        Student result = underTest.getStudentByEmail("fred@gmail.com");
        System.out.println(result);
        Assertions.assertThat(result);
    }


    @Test
    void shouldDeleteStudentById() {
        doNothing().when(this.repository).deleteById((Long) any());
        this.underTest.deleteStudentById(123L);
        verify(this.repository).deleteById((Long) any());
        assertTrue(this.underTest.getAllStudents().isEmpty());
    }


    @Test
    void ShouldGetNumberOfStudents() {
        Student student = mock(Student.class);
        Student student1 = mock(Student.class);

        List<Student> studentList = List.of(student1, student);
        for(Student s: studentList){
            underTest.createStudent(s);
            repository.save(s);
        }

        when(repository.count()).thenReturn(2L);
        Long result = underTest.getNumberOfStudents();
        Assertions.assertThat(result);

    }


}