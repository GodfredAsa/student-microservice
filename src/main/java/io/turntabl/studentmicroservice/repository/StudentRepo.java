package io.turntabl.studentmicroservice.repository;

import io.turntabl.studentmicroservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepo  extends JpaRepository<Student, Long> {

     Student findByEmail(String email);


}
