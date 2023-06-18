package com.example.studysquadv2.Controller;

import com.example.studysquadv2.Model.MyUser;
import com.example.studysquadv2.Model.SessionService;
import com.example.studysquadv2.Model.Student;
import com.example.studysquadv2.Model.Tutor;
import com.example.studysquadv2.Service.SessionServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/service")
@RequiredArgsConstructor
public class SessionServiceController {

    private final SessionServiceService sessionServiceService;

    @GetMapping("/get")
    public ResponseEntity getAllServices(@AuthenticationPrincipal MyUser myUser, Tutor tutor){

        List<SessionService> sessionServices=sessionServiceService.getAll(myUser.getId(), tutor.getId());
        return ResponseEntity.status(200).body(sessionServices);
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@AuthenticationPrincipal MyUser myUser, Tutor tutor,@RequestBody SessionService sessionService){
        sessionServiceService.addSessionService(myUser.getId(), tutor.getId(), sessionService);

        return ResponseEntity.status(200).body("Session Service added");
    }



    @PutMapping("/update/{serviceId}")
    public ResponseEntity UpdateTheService(@AuthenticationPrincipal MyUser myUser,Tutor tutor, @RequestBody SessionService sessionService, @PathVariable Integer serviceId) {

        sessionServiceService.updateTheService(myUser.getId(), tutor.getId(),sessionService,serviceId);
        return ResponseEntity.status(200).body("Service updated");
    }



    @DeleteMapping("/delete/{sessionId}")
    public ResponseEntity deleteTutor(@AuthenticationPrincipal MyUser myUser,Tutor tutor,@PathVariable Integer sessionId) {

        sessionServiceService.deleteSession(myUser.getId(), tutor.getId(), sessionId);
        return ResponseEntity.status(200).body("Session deleted");
    }




    //-----------Assign--------------//
    //assigning method controller
    @PutMapping("/{sesionService_id}/assign/{tutor_id}")
    public ResponseEntity assignSessionServiceToTutor(@PathVariable Integer sesionService_id, @PathVariable Integer tutor_id){
        sessionServiceService.assignServiceToTutor(sesionService_id, tutor_id);
        return ResponseEntity.status(200).body("assign done");
    }



}
