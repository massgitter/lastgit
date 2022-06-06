package com.atlas.skull.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AcademicYearRequest {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
