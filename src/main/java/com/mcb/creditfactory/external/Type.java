package com.mcb.creditfactory.external;

import org.springframework.stereotype.Component;

@Component
public interface Type {

    int approve(CollateralObject object);
}
