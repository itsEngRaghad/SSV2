package com.example.studysquadv2.Repository;

import com.example.studysquadv2.Model.MyUser;
import com.example.studysquadv2.Model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorRepository extends JpaRepository<Tutor,Integer> {

    List<Tutor> findAllByMyUser(MyUser myUser);

    Tutor findTutorById(Integer id);

    Tutor findTutorBySessionServicesId(Integer id);

    Tutor findTutorByName(String name);
    List<Tutor>findTutorByEducationlevel(String educationlevel);

    List<Tutor>findTutorByGender(String gender);

    List<Tutor>findTutorByMajor(String major);

    List<Tutor>findTutorByNationality(String nationality);

    List<Tutor>findTutorByLanguage(String language);



}
