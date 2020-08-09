package com.mcb.creditfactory.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExternalApproveService {

    public int approve(CollateralObject object, Type type) {
        if (object.getDate() == null || object.getYear() == null || object.getValue() == null || object.getType() == null) {
            return -1;
        }

        int code = type.approve(object);
        return code;
    }
}
