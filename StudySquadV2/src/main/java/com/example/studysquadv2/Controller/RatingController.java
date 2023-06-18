package com.example.studysquadv2.Controller;

import com.example.studysquadv2.Model.MyUser;
import com.example.studysquadv2.Model.Rating;
import com.example.studysquadv2.Model.Reservation;
import com.example.studysquadv2.Model.Student;
import com.example.studysquadv2.Service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    //-----------------Get Tutor Rates-----------//

    @GetMapping("/get-rate-by-tutor/{tutorId}")
    public ResponseEntity getAllByTutorID(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer tutorId){
        List<Rating> ratings = ratingService.GetAllByTutorId(myUser.getId(), tutorId);

        return ResponseEntity.status(200).body(ratings);

    }

    //------------------Add Rate--------------------//

    @PostMapping("/add/{tutorId}/{resid}")
    public ResponseEntity addRate(@AuthenticationPrincipal MyUser user, @PathVariable Integer resid, @RequestBody Rating rating, @PathVariable Integer tutorId){

        ratingService.addRate(user.getId(),resid,rating,tutorId);
        return ResponseEntity.status(200).body("Rate Added successfully!");
    }


}
