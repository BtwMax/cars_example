package com.example.cars.service;

import com.example.cars.dto.CarsDto;
import com.example.cars.dto.IncomingCarsDto;
import com.example.cars.enums.Operations;
import com.example.cars.exception.exceptions.NotFoundException;
import com.example.cars.exception.exceptions.ValidationException;
import com.example.cars.mapper.CarsMapper;
import com.example.cars.model.Cars;
import com.example.cars.repo.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarsRepository carsRepository;

    @Autowired
    public CarServiceImpl(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarsDto> getCars(String color, Operations operation, int horsepower) {
        List<Cars> cars;
        switch(operation) {
            case moreThan:
                cars = carsRepository.findByColorAndHorsepowerGreaterThan(color, horsepower);
                break;
            case lessThan:
                cars = carsRepository.findByColorAndHorsepowerLessThan(color, horsepower);
                break;
            case equals:
                cars = carsRepository.findByColorAndHorsepowerEquals(color, horsepower);
                break;
            default:
                return new ArrayList<>();
        }
        return cars.stream()
                .map(CarsMapper::toCarsDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarsDto insertCars(IncomingCarsDto carsDto) {
        Optional<Cars> carsOptional = carsRepository.findByColorAndHorsepower(carsDto.getColor(), carsDto.getHorsepower());
        if(carsOptional.isPresent()) {
            Cars carsOpt = carsOptional.get();
            carsOpt.setQuantity(carsOpt.getQuantity() + carsDto.getQuantity());
            return CarsMapper.toCarsDto(carsRepository.save(carsOpt));
        } else {
            int taxAmount = taxCalculation(carsDto.getHorsepower());
            Cars cars = CarsMapper.toCars(carsDto, taxAmount);
            return CarsMapper.toCarsDto(carsRepository.save(cars));
        }
    }

    @Override
    public CarsDto outcomeCars(IncomingCarsDto carsDto) {
        Optional<Cars> carsOptional = carsRepository.findByColorAndHorsepower(carsDto.getColor(), carsDto.getHorsepower());
        if (carsOptional.isEmpty()) {
            throw new NotFoundException(String.format("Машины с цветом %s и лошадинными слилами %s не найдены",
                    carsDto.getColor(), carsDto.getHorsepower()));
        }
        if (carsOptional.get().getQuantity() < carsDto.getQuantity()) {
            throw new ValidationException("Неудалось провести отпуск машин: недостаточно доступных машин");
        }
        Cars carsOpt = carsOptional.get();
        carsOpt.setQuantity(carsOpt.getQuantity() - carsDto.getQuantity());
        Cars cars = carsRepository.save(carsOpt);
        return CarsMapper.toCarsDto(cars);
    }

    private int taxCalculation(int horsepower) {
        if (horsepower < 100) {
            return horsepower * 12;
        } else if (horsepower < 125) {
            return horsepower * 25;
        } else if (horsepower < 150) {
            return horsepower * 35;
        } else if (horsepower < 175) {
            return horsepower * 45 ;
        } else if (horsepower < 200) {
            return horsepower * 50 ;
        } else if (horsepower < 225) {
            return horsepower * 65 ;
        } else if (horsepower < 250) {
            return horsepower * 75;
        } else {
            return horsepower * 150;
        }
    }
}
