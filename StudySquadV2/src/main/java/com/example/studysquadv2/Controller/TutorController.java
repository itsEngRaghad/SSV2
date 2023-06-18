package com.example.studysquadv2.Controller;

import com.example.studysquadv2.DTO.TutorDTO;
import com.example.studysquadv2.Model.MyUser;
import com.example.studysquadv2.Model.Student;
import com.example.studysquadv2.Model.Tutor;
import com.example.studysquadv2.Service.ReservationService;
import com.example.studysquadv2.Service.TutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tutor")
@RequiredArgsConstructor
public class TutorController {

    private final TutorService tutorService;
    private final ReservationService reservationService;



    @GetMapping("/get-all")
    public ResponseEntity<List<Tutor>> getAllTuts(){
        return ResponseEntity.status(200).body(tutorService.getTutors());
    }


    @GetMapping("/get")
    public ResponseEntity getAllTutors(@AuthenticationPrincipal MyUser myUser){

        List<Tutor> tutors=tutorService.getAllTutors(myUser.getId());
        return ResponseEntity.status(200).body(tutors);
    }


    @PostMapping("/add")
    public ResponseEntity addTutor(@AuthenticationPrincipal MyUser myUser, @RequestBody Tutor tutor){
        tutorService.addTutor(myUser.getId(),tutor);

        return ResponseEntity.status(200).body("Tutor added");
    }

    @PutMapping("/update/{tutorId}")
    public ResponseEntity UpdateTutor(@AuthenticationPrincipal MyUser myUser,@RequestBody Tutor tutor,@PathVariable Integer tutorId) {

        tutorService.updateTutor(myUser.getId(),tutor,tutorId);
        return ResponseEntity.status(200).body("Tutor updated");
    }

    @GetMapping("/get-tutor-by-service/{servId}")
    public ResponseEntity<Tutor> getTutorByServiceID(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer servId){
      Tutor tutor=  tutorService.getTutorByServiceID(myUser.getId(), servId);

        return ResponseEntity.status(200).body(tutor);

    }




    //------------------------------EndPoints-------------------------------------//

    //-----------Change Reservation Status----------------//
    @PutMapping("/update-status/{orderid}/{status}")
    public ResponseEntity updateStatus(@AuthenticationPrincipal MyUser user,@PathVariable Integer orderid,@PathVariable String status){

        reservationService.updateStatus(user.getId() ,orderid,status);
        return ResponseEntity.status(200).body("Status Updated!");
    }


    //--------Get Tutor By Name---------//
    @GetMapping("/get-name/{name}")
    public ResponseEntity getByTitle(@PathVariable String name){
        Tutor tutor=tutorService.findByName(name);
        return ResponseEntity.status(200).body(tutor);
    }

    //--------Get Tutors By Education Level---------//

    @GetMapping("/get-edulevel/{educationlevel}")
    public ResponseEntity getByCategory(@PathVariable String educationlevel){
        List<Tutor> tutors=tutorService.getByEducationLevel(educationlevel);
        return ResponseEntity.status(200).body(tutors);
    }


    //------------Get Tutors By Gender---------//

    @GetMapping("/get-gender/{gender}")
    public ResponseEntity getByGender(@PathVariable String gender){
        List<Tutor> tutors=tutorService.getByGender(gender);
        return ResponseEntity.status(200).body(tutors);
    }

    //------------Get Tutors By Major---------//
    @GetMapping("/get-major/{major}")
    public ResponseEntity getByMajor(@PathVariable String major){
        List<Tutor> tutors=tutorService.getByMajor(major);
        return ResponseEntity.status(200).body(tutors);
    }

    //------------Get Tutors By Nationality---------//

    @GetMapping("/get-nationality/{nationality}")
    public ResponseEntity getByNationality(@PathVariable String nationality){
        List<Tutor> tutors=tutorService.getByNationality(nationality);
        return ResponseEntity.status(200).body(tutors);
    }

    //------------Get Tutors By Language Spoken---------//

    @GetMapping("/get-language/{language}")
    public ResponseEntity getByLanguage(@PathVariable String language){
        List<Tutor> tutors=tutorService.getByLanguage(language);
        return ResponseEntity.status(200).body(tutors);
    }












//    //----------------DTO------------------//
//    @PostMapping("/add-dto")
//    public ResponseEntity addTutorDetails(@RequestBody @Valid TutorDTO dto){
//        tutorService.addTutorDetails(dto);
//        return ResponseEntity.status(200).body("details added");
//    }


}
