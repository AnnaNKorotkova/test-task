package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Getter
@Setter
@AllArgsConstructor
public class AirplaneStrategy implements CollateralStrategy {

    private final AirplaneService airplaneService;

    @Override
    public Long saveCollateral(Collateral object) {
        if (!(object instanceof AirplaneDto)) {
            throw new IllegalArgumentException();
        }

        AirplaneDto airplane = (AirplaneDto) object;
        boolean approved = airplaneService.approve(airplane);
        if (!approved) {
            return null;
        }

        return Optional.of(airplane)
                .map(airplaneService::fromDto)
                .map(airplaneService::save)
                .map(airplaneService::getId)
                .orElse(null);
    }

    @Override
    public Collateral getInfo(Collateral object) {
        if (!(object instanceof AirplaneDto)) {
            throw new IllegalArgumentException();
        }

        return Optional.of((AirplaneDto) object)
                .map(airplaneService::fromDto)
                .map(airplaneService::getId)
                .flatMap(airplaneService::load)
                .map(airplaneService::toDTO)
                .orElse(null);
    }
}
