package com.mcb.creditfactory.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ASSESSED_VALUE")
public class AssessedValue<T extends AbstractModelEntity> extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AbstractModelEntity.class)
    @JoinColumn(name = "object_id", nullable = false)
    private T modelObject;
    private BigDecimal value;

    @Column(name = "date_value")
    private LocalDate dateValue;
//
//    public AssessedValue(Long id, T modelObject, BigDecimal value, LocalDate dateValue) {
//        super(id);
//        this.modelObject = modelObject;
//        this.value = value;
//        this.dateValue = dateValue;
//    }

    public BigDecimal getValue() {
        return value;
    }
}
