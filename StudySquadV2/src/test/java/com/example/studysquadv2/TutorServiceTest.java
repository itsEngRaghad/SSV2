package com.example.studysquadv2;

import com.example.studysquadv2.Model.MyUser;
import com.example.studysquadv2.Model.Student;
import com.example.studysquadv2.Model.Tutor;
import com.example.studysquadv2.Repository.AuthRepository;
import com.example.studysquadv2.Repository.StudentRepository;
import com.example.studysquadv2.Repository.TutorRepository;
import com.example.studysquadv2.Service.StudentService;
import com.example.studysquadv2.Service.TutorService;
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

@ExtendWith(MockitoExtension.class) //to inject mock to get tutor service

public class TutorServiceTest {

    @InjectMocks
    TutorService tutorService;
    @Mock
    TutorRepository tutorRepository;
    @Mock
    AuthRepository authRepository;

    MyUser myUser;

    Tutor tutor1,tutor2,tutor3;

    List<Tutor> tutors;


    @BeforeEach
    void setUp(){
        myUser=new MyUser(null,"username","password","ADMIN",null,null);
        tutor1=new Tutor(null,"Raghad","email","female","Arabic","PhD","SE","saudi",myUser,null,null,null);
        tutor2=new Tutor(null,"Sara","email","female","Arabic","PhD","SE","saudi",myUser,null,null,null);
        tutor3=new Tutor(null,"Aziz","email","male","Arabic","PhD","SE","saudi",myUser,null,null,null);

        tutors=new ArrayList<>();
        tutors.add(tutor1);
        tutors.add(tutor2);
        tutors.add(tutor3);
    }


    @Test
    public void getAllTutorTest(){
        when(tutorRepository.findAll()).thenReturn(tutors);
        List<Tutor> tutorList=tutorService.getTutors();
        Assertions.assertEquals(tutorList,tutors);
        org.junit.jupiter.api.Assertions.assertEquals(3,tutorList.size());
        verify(tutorRepository,times(1)).findAll();

    }

    @Test
    public void AddTutorTest(){

        when(authRepository.findMyUserById(myUser.getId())).thenReturn(myUser);

        tutorService.addTutor(myUser.getId(),tutor3);
        verify(authRepository,times(1)).findMyUserById(myUser.getId());
        verify(tutorRepository,times(1)).save(tutor3);
    }

    @Test
    public void updateTutorTest(){

        when(tutorRepository.findTutorById(tutor1.getId())).thenReturn(tutor1);
        when(authRepository.findMyUserById(myUser.getId())).thenReturn(myUser);

        tutorService.updateTutor(tutor1.getId(),tutor1,myUser.getId());

        verify(tutorRepository,times(1)).findTutorById(tutor1.getId());
        verify(authRepository,times(1)).findMyUserById(myUser.getId());
        verify(tutorRepository,times(1)).save(tutor1);

    }
}
