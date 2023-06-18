package com.example.studysquadv2.Service;

import com.example.studysquadv2.DTO.RatingDTO;
import com.example.studysquadv2.Model.*;
import com.example.studysquadv2.Repository.AuthRepository;
import com.example.studysquadv2.Repository.RatingRepository;
import com.example.studysquadv2.Repository.ReservationRepository;
import com.example.studysquadv2.Repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final AuthRepository authRepository;
    private final TutorRepository tutorRepository;
    private final ReservationRepository reservationRepository;




    //----------Get All Rating By Tutor Id---------//
    public List<Rating> GetAllByTutorId(Integer auth, Integer tutorId){
        return ratingRepository.findAllByTutorId(tutorId);
    }


//    //-------------------ADD Rate-----------------------//
//    public void addRate(Integer studentId, Integer resid,Rating rating,Integer tutorId){
//
//        MyUser student=authRepository.findMyUserById(studentId);
//        Tutor tutor=tutorRepository.findTutorById(tutorId);
//        Reservation reservation=reservationRepository.findReservationById(resid);
//
//
//        if(student==null){
//            throw new RuntimeException("Student not found");
//        }
//
//        if(tutor==null){
//            throw new RuntimeException("Tutor not found");
//        }
//    Rating rating1=new Rating(null,rating.getReview(),rating.getRate(),reservation,tutor);
//
////        rating.setReview(rating.getReview()); //by user
////        rating.setRate(rating.getRate()); //by user
////        rating.setTutor(tutorRepository.findTutorById(tutorId)); //by default
//
//        ratingRepository.save(rating);
//    }


    public void AddRate(RatingDTO ratingDTO,Integer studentId){
        MyUser student=authRepository.findMyUserById(studentId);
        Reservation reservation=reservationRepository.findReservationById(ratingDTO.getReservation_id());
        Tutor tutor=reservation.getTutor();
        if(student==null){
            throw new RuntimeException("Student not found");
        }

        if(reservation==null){
            throw new RuntimeException("Reservation not found");
        }


        if(reservation.getStudent().getId()!=studentId){
            throw new RuntimeException("UnAuthorized");
        }

        Rating rating=new Rating(null,ratingDTO.getReview(), ratingDTO.getRate(), reservation,reservation.getTutor());

        reservation.setRating(rating); //k
//        List<Rating> ratings= (List<Rating>) reservation.getRating();//k
        List<Rating> ratings= (List<Rating>) reservation.getTutor().getRatings();//k

        reservation.getTutor().getRatings().add(rating);//k

        if(ratings.size()>1){
//            reservation.setRating(calculateRate(ratings));
//           tutor.setRatings(calculateRate(ratings));
        }

        ratingRepository.save(rating);
    }


    public Double calculateRate(List<Rating> ratings){
        Integer counter1=0 , counter2=0 , counter3=0 , counter4=0 , counter5=0;
        for (Rating rating:ratings){

            if(rating.getRate()==1)
                counter1++;


            else if (rating.getRate()==2)
                counter2++;


            else if (rating.getRate()==3)
                counter3++;


            else if (rating.getRate()==4)
                counter4++;


            else if (rating.getRate()==5)
                counter5++;
        }
    Double rate=(double)(((1*counter1)+(2*counter2)+(3*counter3)+(4*counter4)+(5*counter5))/ratings.size());
        return rate;
    }

}
