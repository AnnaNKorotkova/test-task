package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.Collateral;
import org.springframework.stereotype.Component;

@Component
public interface CollateralStrategy {
    Long saveCollateral(Collateral object);

    Collateral getInfo(Collateral object);
}
