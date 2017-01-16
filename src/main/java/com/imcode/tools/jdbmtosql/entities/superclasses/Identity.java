package com.imcode.tools.jdbmtosql.entities.superclasses;

import java.io.Serializable;

/**
 * Created by ruslan on 23.10.16.
 */
public interface Identity<ID extends Serializable> extends Serializable {

    ID getIdentity();

    void setIdentity(ID identity);

}