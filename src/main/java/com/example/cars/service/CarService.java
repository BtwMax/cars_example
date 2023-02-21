package com.example.cars.service;

import com.example.cars.model.Cars;
import com.example.cars.model.Result;
import com.example.cars.repo.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarsRepository carsRepository;

    @Autowired
    public CarService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public Result getCars(String color, String operation, int horsepower) {
        List<Cars> cars;
        if(operation.equals("moreThan")) {
            cars = carsRepository.findByColorAndHorsepowerGreaterThan(color, horsepower);
            return new Result(200, "Удалось выполнить запрос", cars);
        } else if (operation.equals("lessThan")) {
            cars = carsRepository.findByColorAndHorsepowerLessThan(color, horsepower);
            return new Result(200, "Удалось выполнить запрос", cars);
        } else if (operation.equals("equals")) {
            cars = carsRepository.findByColorAndHorsepowerEquals(color, horsepower);
            return new Result(200, "Удалось выполнить запрос", cars);
        } else
            return new Result(400, "Параметры запроса отсутствуют или имеют некорректный формат", 0);
    }

    public Result insertCars(Cars cars) {
        Optional<Cars> carsOptional = carsRepository.findByColorAndAndHorsepower(cars.getColor(), cars.getHorsepower());
        if(carsOptional.isPresent()) {
            Cars carsOpt = carsOptional.get();
            carsOpt.setQuantity(carsOpt.getQuantity() + cars.getQuantity());
            carsRepository.save(carsOpt);
        } else {
            carsRepository.save(cars);
        }
        return new Result(200, "Удалось добавить приход", taxCalculation(cars));
    }

    public Result deleteCars(Cars cars) {
        Optional<Cars> carsOptional = carsRepository.findByColorAndAndHorsepower(cars.getColor(), cars.getHorsepower());
        if(carsOptional.isPresent()) {
            if (carsOptional.get().getQuantity() >= cars.getQuantity()) {
                Cars carsOpt = carsOptional.get();
                carsOpt.setQuantity(carsOpt.getQuantity() - cars.getQuantity());
                carsRepository.save(cars);
                if (cars.getQuantity() == cars.getQuantity()) {
                    carsRepository.deleteById(cars.getId());
                }
                return new Result(200, "Удалось добавить приход", taxCalculation(cars));
            } else
                return new Result(400, "Не удалось провести отпуск машин", 0);
        } else
            return new Result(500, "Не удалось провести отпуск машин", 0);
    }

    private int taxCalculation(Cars cars) {
        if (cars.getHorsepower() < 100) {
            return cars.getHorsepower() * 12 * cars.getQuantity();
        } else if (cars.getHorsepower() < 125) {
            return cars.getHorsepower() * 25 * cars.getQuantity();
        } else if (cars.getHorsepower() < 150) {
            return cars.getHorsepower() * 35 * cars.getQuantity();
        } else if (cars.getHorsepower() < 175) {
            return cars.getHorsepower() * 45 * cars.getQuantity();
        } else if (cars.getHorsepower() < 200) {
            return cars.getHorsepower() * 50 * cars.getQuantity();
        } else if (cars.getHorsepower() < 225) {
            return cars.getHorsepower() * 65 * cars.getQuantity();
        } else if (cars.getHorsepower() < 250) {
            return cars.getHorsepower() * 75 * cars.getQuantity();
        } else {
            return cars.getHorsepower() * 150 * cars.getQuantity();
        }
    }
}
