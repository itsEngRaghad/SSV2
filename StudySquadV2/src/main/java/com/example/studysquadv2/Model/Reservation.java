package com.example.studysquadv2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "thereservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Total price can't be null")
    @Column(columnDefinition = "varchar(20) not null")
    private Integer totalprice; //by default

    @NotNull(message = " You have to set session quantity")
    @Column(columnDefinition = "varchar(15) not null")
    private Integer sessionquantity;

    @NotEmpty(message = " You have to set Date ")
    @Column(columnDefinition = "varchar(15) not null")
    private String date;

//    @FutureOrPresent(message = "wrong date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
//    private Date date;

    @NotEmpty(message = " status can't be empty")
    @Column(columnDefinition = "varchar(25) not null check ( status='pending' or status='accepted' or status='declined' or status='done')")
    private String status; //by default


    //--------------------Relations---------------------//

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "reservation")
//    @PrimaryKeyJoinColumn
//    private SessionService sessionService;   //NO NEED


    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private Student student;



    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "reservation")
    @PrimaryKeyJoinColumn
    private Rating rating;

}
