package com.example.studysquadv2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tutor {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "name can't be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;


    @NotEmpty(message = "email can't be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String email;


    @NotEmpty(message = "Gender can't be empty")
    @Column(columnDefinition = "varchar(25) not null check ( gender='male' or gender='female')")
    private String gender;


    @NotEmpty(message = "Language can't be empty")
    @Column(columnDefinition = "varchar(25) not null check ( language='Arabic' or language='English')")
    private String language;


    @NotEmpty(message = "Education Level can't be empty")
    @Column(columnDefinition = "varchar(25) not null check ( educationlevel='PhD' or educationlevel='Bachelor' or educationlevel='Diploma' or educationlevel='HighSchool' or educationlevel='PrimarySchool' or educationlevel='MiddleSchool')")
    private String educationlevel;


    @NotEmpty(message = "Major can't be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String major;


    @NotEmpty(message = "Nationality can't be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String nationality;



    //-------------Relation------------------//

    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser myUser;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutor")
    private Set<SessionService> sessionServices;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "tutor")
    private Set<Reservation> reservationset;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "tutor")
    private Set<Rating> ratings;
}
