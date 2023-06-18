package com.example.studysquadv2;

import com.example.studysquadv2.Model.MyUser;
import com.example.studysquadv2.Model.Tutor;
import com.example.studysquadv2.Repository.AuthRepository;
import com.example.studysquadv2.Repository.TutorRepository;
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
public class TutorRepoTest {

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    AuthRepository myUserRepository;

    Tutor tutor1,tutor2,tutor3;
    MyUser myUser;

    List<Tutor> tutorList;

    @BeforeEach
    void setUp(){
        myUser=new MyUser(null,"username","password","ADMIN",null,null);
        tutor1=new Tutor(null,"Raghad","email","female","Arabic","PhD","SE","saudi",myUser,null,null,null);
        tutor2=new Tutor(null,"Sara","email","female","Arabic","PhD","SE","saudi",myUser,null,null,null);
        tutor3=new Tutor(null,"Aziz","email","male","Arabic","PhD","SE","saudi",myUser,null,null,null);

    }

    @Test
    public void findAllByMyUser(){
        tutorRepository.save(tutor1);
        tutorRepository.save(tutor2);
        tutorRepository.save(tutor3);
        List<Tutor> tutors=tutorRepository.findAllByMyUser(myUser);
        Assertions.assertThat(tutors.get(0).getMyUser().getId()).isEqualTo(myUser.getId());

    }


    @Test
    public void findTutorById(){
        tutorRepository.save(tutor3);
        Tutor tutor=tutorRepository.findTutorById(tutor3.getId());
        Assertions.assertThat(tutor).isEqualTo(tutor3);
    }


    @Test
    public void findTutorByName(){
        tutorRepository.save(tutor3);
        Tutor tutor=tutorRepository.findTutorByName(tutor3.getName());
        Assertions.assertThat(tutor).isEqualTo(tutor3);
    }

    @Test
    public void findTutorByEdu(){
        tutorRepository.save(tutor3);
        List<Tutor> tutor=tutorRepository.findTutorByEducationlevel(tutor3.getEducationlevel());
        Assertions.assertThat(tutor).isEqualTo(tutor3);
    }

    @Test
    public void findTutorByGender(){
        tutorRepository.save(tutor1);
        List<Tutor> tutor=tutorRepository.findTutorByGender(tutor1.getGender());
        Assertions.assertThat(tutor).isEqualTo(tutor1);
    }

    @Test
    public void findTutorByMajor(){
        tutorRepository.save(tutor1);
        tutorRepository.save(tutor2);
        tutorRepository.save(tutor3);
        tutorList= tutorRepository.findTutorByMajor(myUser.getTutor().getMajor());
        Assertions.assertThat(tutorList.get(0).getMajor()).isEqualTo(myUser.getTutor().getMajor());
    }



}
