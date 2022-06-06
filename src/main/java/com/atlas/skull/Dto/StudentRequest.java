package com.atlas.skull.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StudentRequest {

    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String studentId;
    private Long gradeId;
    private String section;
    private Long branchId;
    private Long studentCategoryId;
}
