package com.atlas.skull.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItemResponse {

    private String name;
    private float unitPrice;
    private int vatInclusive;
    private Long active;
}
