package com.atlas.skull.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StudentResponse {

    private String studentId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String grade;
    private String section;

}
