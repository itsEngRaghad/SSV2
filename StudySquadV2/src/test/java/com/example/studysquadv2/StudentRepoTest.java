package com.example.studysquadv2;

import com.example.studysquadv2.Model.MyUser;
import com.example.studysquadv2.Model.Student;
import com.example.studysquadv2.Model.Tutor;
import com.example.studysquadv2.Repository.AuthRepository;
import com.example.studysquadv2.Repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepoTest {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AuthRepository myUserRepository;

    Student student1,student2,student3;
    MyUser myUser;


    @BeforeEach
    void setUp(){
        myUser=new MyUser(null,"username","password","ADMIN",null,null);
        student1=new Student(null,"Raghad","email","female","Arabic","PhD",myUser,null);
        student2=new Student(null,"name","email","female","Arabic","PhD",myUser,null);
        student3=new Student(null,"xx","email","female","Arabic","PhD",myUser,null);

    }


    @Test
    public void findAllByMyUser(){
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        List<Student> students=studentRepository.findAllByMyUser(myUser);
        Assertions.assertThat(students.get(0).getMyUser().getId()).isEqualTo(myUser.getId());

    }


    @Test
    public void findStudentById(){
        studentRepository.save(student3);
        Student student=studentRepository.findStudentById(student3.getId());
        Assertions.assertThat(student).isEqualTo(student3);
    }
}
