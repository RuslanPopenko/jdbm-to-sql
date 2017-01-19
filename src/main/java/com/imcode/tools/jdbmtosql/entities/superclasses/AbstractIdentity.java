package com.imcode.tools.jdbmtosql.entities.superclasses;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ruslan on 23.10.16.
 */
@MappedSuperclass
public abstract class AbstractIdentity<ID extends Serializable> implements Identity<ID>, Serializable {

    @Id
    @Column
    protected ID identity;

    public AbstractIdentity() {
    }

    public AbstractIdentity(ID identity) {
        this.identity = identity;
    }

    @Override
    public ID getIdentity() {
        return identity;
    }

    @Override
    public void setIdentity(ID identity) {
        this.identity = identity;
    }
}