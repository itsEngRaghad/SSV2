package com.example.studysquadv2;

import com.example.studysquadv2.Model.MyUser;
import com.example.studysquadv2.Repository.AuthRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthRepoTest {


    @Autowired
    AuthRepository myUserRepository;

    MyUser myUser;

    @BeforeEach
    void setUp(){
        myUser=new MyUser(null,"username","password","ADMIN",null,null);
    }

    @Test
    public void findUserById(){
        myUserRepository.save(myUser);
        MyUser user=myUserRepository.findMyUserById(myUser.getId());
        Assertions.assertThat(user).isEqualTo(myUser);
    }

    @Test
    public void findUserByUsername(){
        myUserRepository.save(myUser);
        MyUser user=myUserRepository.findMyUserByUsername(myUser.getUsername());
        Assertions.assertThat(user).isEqualTo(myUser);
    }
}
