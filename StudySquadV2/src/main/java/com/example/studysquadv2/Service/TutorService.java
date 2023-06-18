package com.example.studysquadv2.Service;

import com.example.studysquadv2.ApiException.ApiException;
import com.example.studysquadv2.DTO.TutorDTO;
import com.example.studysquadv2.Model.MyUser;
import com.example.studysquadv2.Model.SessionService;
import com.example.studysquadv2.Model.Student;
import com.example.studysquadv2.Model.Tutor;
import com.example.studysquadv2.Repository.AuthRepository;
import com.example.studysquadv2.Repository.SessionServiceRepository;
import com.example.studysquadv2.Repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final AuthRepository authRepository;
    private final TutorRepository tutorRepository;
    private final SessionServiceRepository sessionServiceRepository;



    //----------------CRUD-------------------//
    public List<Tutor> getTutors( ) {
        return tutorRepository.findAll();
    }

    //------------Get All Tutors--------------//
    public List<Tutor> getAllTutors(Integer userId) {
        MyUser myUser=authRepository.findMyUserById(userId);
        return tutorRepository.findAllByMyUser(myUser);
    }


    //--------------Add Tutor------------------//
    public void addTutor( Integer userId, Tutor tutor) {
        MyUser myUser=authRepository.findMyUserById(userId);
        tutor.setMyUser(myUser);
        tutorRepository.save(tutor);
    }


    public void addTutorWithDTO(TutorDTO tutorDTO){

    }

    //-----------------Update Tutor----------------//
    public void updateTutor(Integer id , Tutor newTutor , Integer auth){
        Tutor oldTutor=tutorRepository.findTutorById(id);
        MyUser myUser=authRepository.findMyUserById(auth);

        if (oldTutor==null){
            throw new ApiException("Tutor not found");
        }

        else if(oldTutor.getMyUser().getId()!=auth){
            throw new ApiException("Sorry , You do not have the authority to update this Tutor!");
        }

        newTutor.setId(id);
        newTutor.setMyUser(myUser);

        tutorRepository.save(newTutor);
    }

    public Tutor getTutorByServiceID(Integer auth, Integer serviceId){
        SessionService sessionService=sessionServiceRepository.findSessionServiceById(serviceId);
        MyUser myUser=authRepository.findMyUserById(auth);
        Tutor oldTutor=tutorRepository.findTutorById(sessionService.getId());
        return oldTutor;
    }




    //--------------EndPoints--------------------//


    //--------Get Tutor By Name---------//

    public Tutor findByName(String name){
        Tutor tutorName =tutorRepository.findTutorByName(name);
        if(name==null){
            throw new ApiException("sorry no such Tutor with this Name :(, try another Name");
        }
        return tutorName;
    }

    //--------Get Tutors By Education Level---------//

    public List<Tutor> getByEducationLevel(String educationlevel){
        List<Tutor> tutors= tutorRepository.findTutorByEducationlevel(educationlevel);
        if (educationlevel==null){

            throw new ApiException("Sorry, no such Education Level, try another Education Level :( ");
        }
        return tutors;
    }

    //------------Get Tutors By Gender---------//

    public List<Tutor> getByGender(String gender){
        List<Tutor> tutors= tutorRepository.findTutorByGender(gender);
        if (gender==null){

            throw new ApiException("Sorry, no such Gender, try  male or female :( ");
        }
        return tutors;
    }

    //------------Get Tutors By Major---------//

    public List<Tutor> getByMajor(String major){
        List<Tutor> tutors= tutorRepository.findTutorByMajor(major);
        if (major==null){

            throw new ApiException("Sorry, no such Major, try another major :( ");
        }
        return tutors;
    }


    //------------Get Tutors By Nationality---------//
    public List<Tutor> getByNationality(String nationality){
        List<Tutor> tutors= tutorRepository.findTutorByNationality(nationality);
        if (nationality==null){

            throw new ApiException("Sorry, no such Nationality, try another Nationality :( ");
        }
        return tutors;
    }
    //------------Get Tutors By Language Spoken---------//

    public List<Tutor> getByLanguage(String language){
        List<Tutor> tutors= tutorRepository.findTutorByLanguage(language);
        if (language==null){

            throw new ApiException("Sorry, no such Language, try another Language :( ");
        }
        return tutors;
    }















    //////-------------DTO----------------------/////

//    public void addTutorDetails(TutorDTO dto) {
//        //check id
//        MyUser myUser = authRepository.findMyUserById(dto.getTutor_id());
//        List<SessionService> sessionServices=sessionServiceRepository.findAllByTutorId(dto.getTutor_id());
//        if (myUser == null) {
//            throw new ApiException("sorry can't add details, Tutor not found");
//        }
//        Tutor tutor = new Tutor(null, dto.getName(), dto.getEmail(), dto.getGender(), dto.getLanguage(), dto.getEducationlevel(), dto.getMajor(), dto.getNationality(),myUser,null,null);
//        tutorRepository.save(tutor);
//    }
}
