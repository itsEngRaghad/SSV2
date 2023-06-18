package com.example.studysquadv2;

import com.example.studysquadv2.ApiResponse.ApiResponse;
import com.example.studysquadv2.Model.MyUser;
import com.example.studysquadv2.Model.Tutor;
import com.example.studysquadv2.Service.TutorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TutorControllerTest.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class TutorControllerTest {


    @MockBean
    TutorService tutorService;

    @Autowired
    MockMvc mockMvc;

    Tutor tutor1,tutor2,tutor3;
    MyUser myUser;

    ApiResponse apiResponse;

    List<Tutor> tutors,tutorList;


    @BeforeEach
    void setUp(){
        myUser=new MyUser(null,"username","password","ADMIN",null,null);
        tutor1=new Tutor(null,"Raghad","email","female","Arabic","PhD","SE","saudi",myUser,null,null,null);
        tutor2=new Tutor(null,"Sara","email","female","Arabic","PhD","SE","saudi",myUser,null,null,null);
        tutor3=new Tutor(null,"Aziz","email","male","Arabic","PhD","SE","saudi",myUser,null,null,null);

        tutors= Arrays.asList(tutor1);
        tutorList=Arrays.asList(tutor2);
    }

    @Test
    public void GetAllTutor() throws Exception {
        Mockito.when(tutorService.getTutors()).thenReturn(tutors);
        mockMvc.perform(get("/api/v1/tutor/get-all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("tutor1"));
    }


//    @Test
//    public void testAddTutor() throws  Exception {
//        mockMvc.perform(post("/api/v1/tutor/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content( new ObjectMapper().writeValueAsString(tutor1)))
//                .andExpect(status().isOk());
//
//    }




}
