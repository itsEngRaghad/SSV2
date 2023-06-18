package com.example.studysquadv2.Repository;

import com.example.studysquadv2.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {

    Rating findRatingById(Integer id);

    List<Rating>findAllByTutorId(Integer id);
}
