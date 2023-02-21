package com.example.cars.controllers;

import com.example.cars.model.Cars;
import com.example.cars.model.Result;
import com.example.cars.service.CarService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarsController {

    private final CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public Result getCars(@RequestParam("color") String color,
                              @RequestParam("operation") String operation,
                              @RequestParam("horsepower") int horsepower) {
        if (color == null || color.equals("") || horsepower <= 0 || operation.equals("")) {
            return new Result(400, "Параметры запроса отсутствуют или имеют некорректный формат", 0);
        }
        return carService.getCars(color, operation, horsepower);
    }

    @PostMapping ("/cars/income")
    public Result income(@RequestBody Cars cars) {
        if (cars.IsInvalid()) {
            return new Result(400, "Параметры запроса отсутствуют или имеют некорректный формат", 0);
        }
         return carService.insertCars(cars);
    }

    @PostMapping("/cars/outcome")
    public Result outcome(@RequestBody Cars cars) {
        if (cars.IsInvalid()) {
            return new Result(400, "Параметры запроса отсутствуют или имеют некорректный формат", 0);
        }
        return carService.deleteCars(cars);
    }
}
