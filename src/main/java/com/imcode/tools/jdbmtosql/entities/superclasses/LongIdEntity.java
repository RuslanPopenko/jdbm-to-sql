package com.imcode.tools.jdbmtosql.entities.superclasses;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by ruslan on 16.01.17.
 */
@MappedSuperclass
public abstract class LongIdEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column
    protected Long id;

    public LongIdEntity() {
    }

    public LongIdEntity(Long identity) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}