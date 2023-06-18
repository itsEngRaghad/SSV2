package com.example.studysquadv2.Repository;

import com.example.studysquadv2.Model.Reservation;
import com.example.studysquadv2.Model.Student;
import com.example.studysquadv2.Model.Tutor;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

//    List<Reservation> findReservationByStudents(Student student);

    List<Reservation> findAllByStudentId(Integer id);
    List<Reservation> findAllByTutorId(Integer id);

//    List<Reservation> findReservationByTutors(Tutor tutor);

    Reservation findReservationById(Integer id);


}
