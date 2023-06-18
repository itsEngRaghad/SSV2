package com.example.studysquadv2.Repository;

import com.example.studysquadv2.Model.MyUser;
import com.example.studysquadv2.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findStudentById(Integer id);

    List<Student> findAllByMyUser(MyUser myUser);
}
