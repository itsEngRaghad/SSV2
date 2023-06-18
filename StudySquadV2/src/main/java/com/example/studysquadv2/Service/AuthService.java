package com.example.studysquadv2.Service;

import com.example.studysquadv2.Model.MyUser;
import com.example.studysquadv2.Model.Student;
import com.example.studysquadv2.Model.Tutor;
import com.example.studysquadv2.Repository.AuthRepository;
import com.example.studysquadv2.Repository.StudentRepository;
import com.example.studysquadv2.Repository.TutorRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final AuthRepository authRepository;
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;

    public List<MyUser> getAllUser(){

        return authRepository.findAll();
    }


//    public void register(MyUser myUser){
//        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
//        myUser.setPassword(hash);
////        myUser.setRole("TUTOR");
//        authRepository.save(myUser);
//    }


    public void studentRegister(MyUser myUser){
        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
        myUser.setRole("STUDENT");
        authRepository.save(myUser);
    }

    public void TutorRegister(MyUser myUser){
        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
        myUser.setRole("TUTOR");
        authRepository.save(myUser);
    }

}
