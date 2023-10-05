package com.example.cars.controllers;

import com.example.cars.dto.CarsDto;
import com.example.cars.dto.IncomingCarsDto;
import com.example.cars.enums.Operations;
import com.example.cars.exception.exceptions.ValidationException;
import com.example.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CarsController {

    private final CarService carService;

    @Autowired
    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<CarsDto> getCars(@RequestParam("color") String color,
                                 @RequestParam("operation") String operation,
                                 @RequestParam("horsepower") int horsepower) {
        Operations operationState = Operations.from(operation)
                .orElseThrow(() -> new ValidationException("Неизвестный параметр operation"));
        return carService.getCars(color, operationState, horsepower);
    }

    @PostMapping ("/cars/income")
    public CarsDto income(@RequestBody @Valid IncomingCarsDto carsDto) {
         return carService.insertCars(carsDto);
    }

    @PostMapping("/cars/outcome")
    public CarsDto outcome(@RequestBody @Valid IncomingCarsDto carsDto) {
        return carService.outcomeCars(carsDto);
    }
}
