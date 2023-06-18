package com.example.studysquadv2.Service;

import com.example.studysquadv2.ApiException.ApiException;
import com.example.studysquadv2.DTO.StudentDTO;
import com.example.studysquadv2.Model.MyUser;
import com.example.studysquadv2.Model.Student;
import com.example.studysquadv2.Repository.AuthRepository;
import com.example.studysquadv2.Repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    private final AuthRepository authRepository;

    //----------------CRUD-------------------//

    public List<Student> getStudents( ) {
        return studentRepository.findAll();
    }

    //------------Get All Students--------------//
    public List<Student> getAllStudents(Integer userId) {
        MyUser myUser=authRepository.findMyUserById(userId);
        return studentRepository.findAllByMyUser(myUser);
    }


    //--------------Add Student------------------//
    public void addStudent( Integer userId, Student student) {
        MyUser myUser=authRepository.findMyUserById(userId);
        student.setMyUser(myUser);
        studentRepository.save(student);
    }

    //-----------------Update Student----------------//
    public void updateStudent(Integer id , Student newStudent , Integer auth){
        Student oldStudent=studentRepository.findStudentById(id);
        MyUser myUser=authRepository.findMyUserById(auth);

        if (oldStudent==null){
            throw new ApiException("Student not found");
        }

        else if(oldStudent.getMyUser().getId()!=auth){
            throw new ApiException("Sorry , You do not have the authority to update this Student Account!");
        }

        newStudent.setId(id);
        newStudent.setMyUser(myUser);

        studentRepository.save(newStudent);
    }


//    //--------- Student Register---------//
//    public Void addStudentDTO(StudentDTO studentDTO){
//        String hash=new BCryptPasswordEncoder().encode(studentDTO.getPassword());
//        myUser.setPassword(hash);
//    }

//////-------------DTO----------------------/////

//    public void addStudentDetails(StudentDTO dto) {
//        //check id
//        MyUser myUser = authRepository.findMyUserById(dto.getStudent_id());
//        if (myUser == null) {
//            throw new ApiException("sorry can't add details, Student not found");
//        }
//        Student student = new Student(null, dto.getName(), dto.getEmail(), dto.getGender(), dto.getLanguage(), dto.getEducationlevel(), myUser,null);
//        studentRepository.save(student);
//
//    }







}
