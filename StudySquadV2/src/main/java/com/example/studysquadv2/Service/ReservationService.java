package com.example.studysquadv2.Service;

import com.example.studysquadv2.ApiException.ApiException;
import com.example.studysquadv2.Model.*;
import com.example.studysquadv2.Repository.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final SessionServiceRepository sessionServiceRepository;
    private final TutorRepository tutorRepository;
    private final StudentRepository studentRepository;
    private final AuthRepository authRepository;






    //---------Get All By Student ID-------------//
    public List<Reservation>GetAllByStudentId(Integer auth,Integer studentId){
        return reservationRepository.findAllByStudentId(studentId);
    }

    //----------Get All Reservation By Tutor Id---------//
    public List<Reservation>GetAllByTutorId(Integer auth,Integer tutorId){
        return reservationRepository.findAllByTutorId(tutorId);
    }


    //---------------ADD Reservation---------//

    public void addOrder(Integer studentId, Reservation reservation,Integer serviceId){
//        Student student=studentRepository.findStudentById(studentId);
        MyUser student=authRepository.findMyUserById(studentId);
        SessionService sessionService=sessionServiceRepository.findSessionServiceById(serviceId);

//        if(student==null||sessionService==null){
//            throw new RuntimeException("not found");
//        }
        if(student==null){
            throw new RuntimeException("Student not found");
        }

        if(sessionService==null){
            throw new RuntimeException("Service not found");
        }
//        System.out.println(reservation.getDate());
        reservation.setStatus("pending"); //by default
        reservation.setDate(reservation.getDate());
        reservation.setTotalprice(sessionService.getPrice()*reservation.getSessionquantity()); //by default+user quantity
        reservation.setStudent(student.getStudent()); //by default
        reservation.setTutor(tutorRepository.findTutorBySessionServicesId(serviceId)); //by default

        reservationRepository.save(reservation);
    }


    //--------------- Update Reservation---------//

    public void updateOrder(Integer studentId, Reservation reservation,Integer orderId,Integer serviceId){
        Student student=studentRepository.findStudentById(studentId);
        Reservation reservation1=reservationRepository.findReservationById(orderId);
        SessionService sessionService=sessionServiceRepository.findSessionServiceById(serviceId);

        if(student==null){
            throw new RuntimeException("Student not found");
        }

        if(reservation1==null){
            throw new RuntimeException("Order not found");
        }

        if(sessionService==null){
            throw new RuntimeException("Service not found");
        }

        if(reservation1.getStatus().equals("done")) {
            throw new ApiException("You Can't update This Order it's already Done");
        }
        reservation1.setDate(reservation.getDate());

        reservation1.setStatus("pending");
      reservation1.setSessionquantity(reservation.getSessionquantity());//re set session quantity
        reservation1.setTotalprice(sessionService.getPrice()*reservation.getSessionquantity());
        reservation1.setStudent(student);
        reservation1.setTutor(tutorRepository.findTutorBySessionServicesId(serviceId));



        reservationRepository.save(reservation1);
    }


    //--------------- Delete Reservation---------//

//    //In Delete endpoint, check order status if its in progress or complete throw an exception
//    public void deleteOrder(Integer studentId, Integer orderid){
//        //check if Order  exist
//
//        Reservation oldOrder=reservationRepository.findReservationById(orderid);
//        if(oldOrder==null) {
//            throw new ApiException("sorry no such Order to Delete");
//        }
//
//        if(oldOrder.getStudent().getId()!=studentId) {
//            throw new ApiException("unAuthorized, this Order doesn't belong to you!");
//        }
//
//        if(oldOrder.getStatus().equals("done")) {
//            throw new ApiException("You Can't Cancel This Order it's already Done");
//        }
//
//
//        //else, if found
//        oldOrder.setStatus("cancelled");
//        reservationRepository.save(oldOrder);
//    }


    //-------------Change Status for tutor--------------//
    public void updateStatus(Integer userId, Integer orderid ,String status){

        Reservation oldOrder=reservationRepository.findReservationById(orderid);
        if(oldOrder==null) {
            throw new ApiException("sorry no such Order to update");
        }

        if(oldOrder.getTutor().getId()!=userId) {
            throw new ApiException("unAuthorized, this Order doesn't belong to you!");
        }

        oldOrder.setStatus(status);
        reservationRepository.save(oldOrder);
    }
}
