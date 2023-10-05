package com.example.cars.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarsDto {

    private long id;

    private String color;

    private Integer horsepower;

    private Integer quantity;

    private Integer taxAmount;
}
