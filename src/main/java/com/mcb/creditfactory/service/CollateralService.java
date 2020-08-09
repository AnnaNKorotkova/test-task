package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.service.car.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CollateralService {

    private final CarService carService;
    private final AirplaneService airplaneService;

    public Long saveCollateral(Collateral object) {
        CollateralStrategy strategy = getStrategy(object);
        return strategy.saveCollateral(object);
    }

    public Collateral getInfo(Collateral object) {
        CollateralStrategy strategy = getStrategy(object);
        return strategy.getInfo(object);
    }

    public CollateralStrategy getStrategy(Collateral object) {
        CollateralStrategy col;
        Class<?> clazz = object.getClass();
        if (CarDto.class.equals(clazz)) {
            col = new CarStrategy(carService);
        } else if (AirplaneDto.class.equals(clazz)) {
            col = new AirplaneStrategy(airplaneService);
        } else {
            throw new IllegalArgumentException();
        }
        return col;
    }
}
