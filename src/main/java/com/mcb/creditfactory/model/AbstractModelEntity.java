package com.mcb.creditfactory.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "model")
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractModelEntity extends AbstractBaseEntity {

    protected String brand;
    protected String model;
    @Column(name = "year_of_issue")
    protected Short yearOfIssue;

    @OneToMany(mappedBy = "modelObject", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    protected Set<AssessedValue<? extends AbstractModelEntity>> setValues;


    public AbstractModelEntity(Long id, String brand, String model, Short yearOfIssue) {
        super(id);
        this.brand = brand;
        this.model = model;
        this.yearOfIssue = yearOfIssue;
    }
}
