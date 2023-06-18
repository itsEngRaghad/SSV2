package com.example.studysquadv2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SessionService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Session Service Can't Be Empty")
    @Column(columnDefinition = "varchar(25) not null check ( sessionservice='online' or sessionservice='studentplace' or sessionservice='tutorplace')")
    private String sessionservice;


    @NotNull(message = "price can't be null")
    @Column(columnDefinition = "varchar(15) not null")
    private Integer price;


    //----------Relation-------------//
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "tutor_id")//,referencedColumnName = "id"
    private Tutor tutor;

//    @OneToOne
//    @MapsId
//    @JsonIgnore
//    private Reservation reservation;


}
