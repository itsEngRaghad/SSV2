package com.example.studysquadv2.Controller;

import com.example.studysquadv2.DTO.StudentDTO;
import com.example.studysquadv2.Model.MyUser;
import com.example.studysquadv2.Model.Student;
import com.example.studysquadv2.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Student>> getAllStus(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @GetMapping("/get")
    public ResponseEntity getAllStudents(@AuthenticationPrincipal MyUser myUser){

        List<Student> students=studentService.getAllStudents(myUser.getId());
        return ResponseEntity.status(200).body(students);
    }


    @PostMapping("/add")
    public ResponseEntity addStudent(@AuthenticationPrincipal MyUser myUser, @RequestBody Student student){
        studentService.addStudent(myUser.getId(),student);

        return ResponseEntity.status(200).body("Student added");
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity UpdateTutor(@AuthenticationPrincipal MyUser myUser,@RequestBody Student student,@PathVariable Integer studentId) {

        studentService.updateStudent(myUser.getId(),student,studentId);
        return ResponseEntity.status(200).body("Student updated");
    }

    //----------------DTO------------------//
//    @PostMapping("/add-dto")
//    public ResponseEntity addStudentDetails(@RequestBody@Valid StudentDTO dto){
//        studentService.addStudentDetails(dto);
//        return ResponseEntity.status(200).body("details added");
//    }



}
