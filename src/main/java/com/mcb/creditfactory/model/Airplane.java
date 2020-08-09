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
@Table(name = "AIRPLANE")
public class Airplane extends AbstractModelEntity {

    private String manufacturer;
    private Integer fuelCapacity;
    private Integer seats;

    public Airplane(
            Long id,
            String brand,
            String model,
            Short yearOfIssue,
            String manufacturer,
            Integer fuelCapacity,
            Integer seats
    ) {
        super(id, brand, model, yearOfIssue);
        this.manufacturer = manufacturer;
        this.fuelCapacity = fuelCapacity;
        this.seats = seats;
    }
}
