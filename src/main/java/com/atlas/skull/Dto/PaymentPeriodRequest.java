package com.atlas.skull.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentPeriodRequest {

    private String name;
    private Long academicYearId;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
