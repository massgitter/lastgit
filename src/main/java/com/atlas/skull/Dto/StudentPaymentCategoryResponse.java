package com.atlas.skull.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StudentPaymentCategoryResponse {

    private Long id;
    private String paymentCategory;
    private String studentCategory;
    private float price;
}
