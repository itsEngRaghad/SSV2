package com.example.studysquadv2;

import com.example.studysquadv2.Model.MyUser;
import com.example.studysquadv2.Model.Student;
import com.example.studysquadv2.Repository.AuthRepository;
import com.example.studysquadv2.Repository.StudentRepository;
import com.example.studysquadv2.Service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //to inject mock to get student service

public class StudentServiceTest {



    @InjectMocks
    StudentService studentService;
    @Mock
    StudentRepository studentRepository;
    @Mock
    AuthRepository authRepository;

    MyUser myUser;

    Student student1,student2,student3;

    List<Student> students;

    @BeforeEach
    void setUp() {
        myUser=new MyUser(null,"username","password","ADMIN",null,null);
        student1=new Student(null,"Raghad","email","female","Arabic","PhD",myUser,null);
        student2=new Student(null,"name","email","female","Arabic","PhD",myUser,null);
        student3=new Student(null,"xx","email","female","Arabic","PhD",myUser,null);


        students=new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
    }

    @Test
    public void getAllStudentTest(){
        when(studentRepository.findAll()).thenReturn(students);
        List<Student> studentList=studentService.getStudents();
        Assertions.assertEquals(studentList,students);
        org.junit.jupiter.api.Assertions.assertEquals(3,studentList.size());
        verify(studentRepository,times(1)).findAll();

    }

    @Test
    public void AddStudentTest(){

        when(authRepository.findMyUserById(myUser.getId())).thenReturn(myUser);

        studentService.addStudent(myUser.getId(),student3);
        verify(authRepository,times(1)).findMyUserById(myUser.getId());
        verify(studentRepository,times(1)).save(student3);
    }

    @Test
    public void updateStudentTest(){

        when(studentRepository.findStudentById(student1.getId())).thenReturn(student1);
        when(authRepository.findMyUserById(myUser.getId())).thenReturn(myUser);

        studentService.updateStudent(student1.getId(),student1,myUser.getId());

        verify(studentRepository,times(1)).findStudentById(student1.getId());
        verify(authRepository,times(1)).findMyUserById(myUser.getId());
        verify(studentRepository,times(1)).save(student1);

    }


}
