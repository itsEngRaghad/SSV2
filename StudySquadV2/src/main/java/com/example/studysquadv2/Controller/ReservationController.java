package com.example.studysquadv2.Controller;

import com.example.studysquadv2.Model.*;
import com.example.studysquadv2.Service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;



    @GetMapping("/get-reservation-by-student/{studentId}")
    public ResponseEntity getAllByStudentID(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer studentId){
        List<Reservation> reservation=  reservationService.GetAllByStudentId(myUser.getId(), studentId);

        return ResponseEntity.status(200).body(reservation);

    }

    @GetMapping("/get-reservation-by-tutor/{tutortId}")
    public ResponseEntity getAllByTutorID(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer tutortId){
        List<Reservation> reservation=  reservationService.GetAllByTutorId(myUser.getId(), tutortId);

        return ResponseEntity.status(200).body(reservation);

    }


    //------------------Add Reservation--------------------//

    @PostMapping("/add/{serviceId}")
    public ResponseEntity addOrder(@AuthenticationPrincipal MyUser user,Student student, @RequestBody Reservation reservation,@PathVariable Integer serviceId){

        reservationService.addOrder(user.getId(),reservation,serviceId);
        return ResponseEntity.status(200).body("Order Generated successfully!");
    }

    //---------------Update Reservation--------------------//
    @PutMapping("/update/{orderId}/{serviceId}")
    public ResponseEntity updateOrder(@AuthenticationPrincipal MyUser user,Student student, @RequestBody Reservation reservation,@PathVariable Integer orderId,@PathVariable Integer serviceId){

        reservationService.updateOrder(user.getId(),reservation,orderId,serviceId);
        return ResponseEntity.status(200).body("Order Updated successfully!");
    }

    //-----------------Delete Reservation--------------------//
//    @PutMapping("/delete/{orderId}")
//    public ResponseEntity deleteOrder(@AuthenticationPrincipal MyUser user,Student student,@PathVariable Integer orderId){
//
//        reservationService.deleteOrder(user.getId(),orderId);
//        return ResponseEntity.status(200).body("Order Cancelled successfully!");
//    }

}
