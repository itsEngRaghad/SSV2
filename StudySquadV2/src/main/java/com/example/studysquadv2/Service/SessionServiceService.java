package com.example.studysquadv2.Service;

import com.example.studysquadv2.ApiException.ApiException;
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
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SessionServiceService {
    private final AuthRepository authRepository;
    private final TutorRepository tutorRepository;
    private final SessionServiceRepository sessionServiceRepository;

//----------------CRUD-------------------//


    //------------Get All Session By Tutor--------------//
//    public List<SessionService> getAllSessionServicesByTutor(Tutor tutor) {
//        return sessionServiceRepository.findSessionServiceByTutor(tutor);
//    }//this method will be used to represent all session services that teacher put



    public List<SessionService> getAll(Integer userId,Integer tutorId) {
        MyUser myUser=authRepository.findMyUserById(userId);
        Tutor tutor=tutorRepository.findTutorById(tutorId);

        return sessionServiceRepository.findAllByTutorId(tutorId);
    }//try this


//
//
//    //------------Get  one Session By Id and Tutor--------------//
//    public SessionService getSessionServicesByIdAndTutor(Integer id,Tutor tutor) {
//        return sessionServiceRepository.findSessionServiceByIdAndTutor(id,tutor);
//    }
//

    //--------------Add Session Service By Tutor------------------//
    public void addSessionService(Integer userId, Integer tutorId ,SessionService sessionService) {
        MyUser myUser=authRepository.findMyUserById(userId);
        Tutor tutor1=tutorRepository.findTutorById(tutorId);
//        sessionService.setTutor(tutor); Tutor tutor in param
        sessionService.setTutor(tutor1);

        sessionServiceRepository.save(sessionService);
    }

    //-----------------Update Session Service ----------------//
    public void updateTheService(Integer serviceid ,Integer tutorId, SessionService newSessionService , Integer auth){
        SessionService oldService=sessionServiceRepository.findSessionServiceById(serviceid);
        Tutor tutor=tutorRepository.findTutorById(tutorId);
        MyUser myUser=authRepository.findMyUserById(auth);

        if (oldService==null){
            throw new ApiException("Session not found");
        }

        else if(oldService.getTutor().getMyUser().getId()!=auth){
            throw new ApiException("Sorry , You do not have the authority to update this Service !");
        }

        newSessionService.setId(serviceid);
        newSessionService.setTutor(tutor);

        sessionServiceRepository.save(newSessionService);
    }

    public void deleteSession( Integer userId, Integer tutorId,Integer sessionId) {
        SessionService sessionService=sessionServiceRepository.findSessionServiceById(sessionId);

        if(sessionService.getTutor().getMyUser().getId()!=userId){
            throw  new ApiException("you dont have authority to delete Session");
        }

        sessionServiceRepository.delete(sessionService);
    }


    //--------------Assign----------------//
    public void assignServiceToTutor(Integer tutor_id, Integer sessionService_id){

        //check the two ids of both before assigning
        Tutor tutor=tutorRepository.findTutorById(tutor_id);
        SessionService sessionService=sessionServiceRepository.findSessionServiceById(sessionService_id);

        if(tutor==null||sessionService==null){
            throw new ApiException("id wrong, can't assign Tutor to Service");
        }
        sessionService.setTutor((tutor));
//        tutor.setSessionServices((Set<SessionService>) sessionService);
        sessionServiceRepository.save(sessionService);
    }



}
