package com.example.studysquadv2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDTO {

    private Integer student_id;
    private String name;
    private String email;
    private String gender;
    private String language;
    private String educationlevel;
}
