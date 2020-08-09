package com.mcb.creditfactory.model;

import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.util.Assert;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
@NoArgsConstructor
public abstract class AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public long id() {
        Assert.notNull(id, "Entity must has id");
        return id;
    }

    protected AbstractBaseEntity(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : Long.hashCode(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }
}
