package com.mcb.creditfactory.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "CAR")
public class Car extends AbstractModelEntity {

    private Double power;

    public Car(Long id, String brand, String model, Double power, Short yearOfIssue) {
        super(id, brand, model, yearOfIssue);
        this.power = power;
    }
}
