package com.atlas.skull.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentPeriodResponse {

    private Long id;
    private String AcademicYear;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
