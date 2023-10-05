package com.example.cars.service;

import com.example.cars.dto.CarsDto;
import com.example.cars.dto.IncomingCarsDto;
import com.example.cars.enums.Operations;

import java.util.List;

public interface CarService {

    List<CarsDto> getCars(String color, Operations operation, int horsepower);

    CarsDto insertCars(IncomingCarsDto carsDto);

    CarsDto outcomeCars(IncomingCarsDto carsDto);
}
