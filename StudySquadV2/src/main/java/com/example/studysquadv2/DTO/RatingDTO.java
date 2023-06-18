package com.example.studysquadv2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingDTO {

    private Integer reservation_id;
    private Integer rate;
    private String review;

}
