package com.example.studysquadv2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "name can't be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;


    @NotEmpty(message = "email can't be empty")
//    @Email
    @Column(columnDefinition = "varchar(50) not null")
    private String email;


    @NotEmpty(message = "Gender can't be empty")
    @Column(columnDefinition = "varchar(15) not null check ( gender='male' or gender='female')")
    private String gender;

    @NotEmpty(message = "Language can't be empty")
    @Column(columnDefinition = "varchar(25) not null check ( language='Arabic' or language='English')")
    private String language;

    @NotEmpty(message = "Education Level can't be empty")
    @Column(columnDefinition = "varchar(25) not null check ( educationlevel='PhD' or educationlevel='Bachelor' or educationlevel='Diploma' or educationlevel='HighSchool' or educationlevel='PrimarySchool' or educationlevel='MiddleSchool')")
    private String educationlevel;


    //----------------Relation------------------//
    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser myUser;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
    private Set<Reservation> reservationset;
}
