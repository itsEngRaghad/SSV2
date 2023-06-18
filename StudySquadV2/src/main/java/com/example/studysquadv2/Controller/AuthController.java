package com.example.studysquadv2.Controller;

import com.example.studysquadv2.Model.MyUser;
import com.example.studysquadv2.Model.Student;
import com.example.studysquadv2.Model.Tutor;
import com.example.studysquadv2.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

//    @PostMapping("/register")
//    public ResponseEntity register(@RequestBody MyUser myUser){
//        authService.register(myUser);
//
//        return ResponseEntity.status(200).body("User registered");
//    }

    @PostMapping("/register/student")
    public ResponseEntity studentRegister(@RequestBody MyUser myUser){
        authService.studentRegister(myUser);

        return ResponseEntity.status(200).body("STUDENT registered");
    }

    @PostMapping("/register/tutor")
    public ResponseEntity tutorRegister(@RequestBody MyUser myUser){
        authService.TutorRegister(myUser);

        return ResponseEntity.status(200).body("TUTOR registered");
    }

    @PostMapping("/login")
    public ResponseEntity login(){

        return ResponseEntity.status(200).body("logged in");
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){

        return ResponseEntity.status(200).body("log out");
    }

}
