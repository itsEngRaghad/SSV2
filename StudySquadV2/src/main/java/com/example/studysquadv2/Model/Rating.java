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
public class Rating {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Review can't be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String review;

    @NotNull(message = "Rate can't be null")
    @Column(columnDefinition = "varchar(20) not null ")//check ( rate <= 5 )
    private Integer rate;


    //-----------------Relation------------------//

    @OneToOne
    @MapsId
    @JsonIgnore
    private Reservation reservation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
}
