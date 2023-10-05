package com.example.cars.repo;

import com.example.cars.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarsRepository extends JpaRepository<Cars, Long> {
    List<Cars> findByColorAndHorsepowerLessThan(String color, int horsePower);
    List<Cars> findByColorAndHorsepowerGreaterThan(String color, int horsePower);
    List<Cars> findByColorAndHorsepowerEquals(String color, int horsepower);
    Optional<Cars> findByColorAndHorsepower(String color, int horsepower);
}
