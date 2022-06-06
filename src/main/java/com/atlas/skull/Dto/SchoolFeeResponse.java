package com.atlas.skull.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SchoolFeeResponse {

    private String academicYear;
    private String paymentPeriod;
    private float amount;
    private String status;
}
