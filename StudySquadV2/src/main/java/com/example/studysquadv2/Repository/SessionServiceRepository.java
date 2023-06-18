package com.example.studysquadv2.Repository;

import com.example.studysquadv2.Model.SessionService;
import com.example.studysquadv2.Model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionServiceRepository extends JpaRepository<SessionService,Integer> {

    SessionService findSessionServiceById(Integer id);
    SessionService deleteSessionServiceByIdAndTutor(Integer id, Tutor tutor);
//    SessionService updateSessionServiceByIdAndTutor(Integer id, Tutor tutor);

    SessionService findSessionServiceByIdAndTutor(Integer id,Tutor tutor);

    List<SessionService> findAllByTutorId(Integer id);

    List<SessionService>findSessionServiceByTutor(Tutor tutor);

}
