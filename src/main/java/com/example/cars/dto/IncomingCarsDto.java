package com.example.cars.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomingCarsDto {

    @NotBlank
    private String color;

    @Min(value = 1)
    @NotNull
    private Integer horsepower;

    @Min(value = 1)
    @NotNull
    private Integer quantity;
}
