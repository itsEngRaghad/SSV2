package com.example.studysquadv2.Repository;

import com.example.studysquadv2.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<MyUser,Integer> {

    MyUser findMyUserByUsername(String username);


    MyUser findMyUserById(Integer id);
}
