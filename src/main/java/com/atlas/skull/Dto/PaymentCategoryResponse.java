package com.atlas.skull.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentCategoryResponse {

    private Long id;
    private String academicYear;
    private String name;
    private String description;
}
