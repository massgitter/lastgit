package com.atlas.skull.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StudentPaymentCategoryRequest {

    private Long paymentCategoryId;
    private Long studentCategoryId;
    private float price;
}
