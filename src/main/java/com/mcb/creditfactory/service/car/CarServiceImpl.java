package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.CarType;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AssessedValue;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final ExternalApproveService approveService;
    private final CarRepository carRepository;
    private final CarType type;

    @Override
    public boolean approve(CarDto dto) {
        return approveService.approve(new CarAdapter(dto), type) == 0;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> load(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car fromDto(CarDto dto) {
        return new Car(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getPower(),
                dto.getYear()
        );
    }

    @Override
    public CarDto toDTO(Car car) {
        AssessedValue<Car> assessedValue =
                (AssessedValue<Car>) car.getSetValues().stream().max(Comparator.comparing(AssessedValue::getDateValue)).get();
        return new CarDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getPower(),
                car.getYearOfIssue(),
                assessedValue.getValue(),
                assessedValue.getDateValue()
        );
    }

    @Override
    public Long getId(Car car) {
        return car.getId();
    }
}
