package com.example.cars.mapper;

import com.example.cars.dto.CarsDto;
import com.example.cars.dto.IncomingCarsDto;
import com.example.cars.model.Cars;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CarsMapper {

    public Cars toCars (IncomingCarsDto carsDto, int taxAmount) {
        return Cars.builder()
                .color(carsDto.getColor())
                .horsepower(carsDto.getHorsepower())
                .quantity(carsDto.getQuantity())
                .taxAmount(taxAmount)
                .build();
    }

    public CarsDto toCarsDto(Cars cars) {
        return CarsDto.builder()
                .id(cars.getId())
                .color(cars.getColor())
                .horsepower(cars.getHorsepower())
                .quantity(cars.getQuantity())
                .taxAmount(cars.getTaxAmount())
                .build();
    }
}
