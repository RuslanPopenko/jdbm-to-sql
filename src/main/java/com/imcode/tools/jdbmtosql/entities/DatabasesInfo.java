package com.imcode.tools.jdbmtosql.entities;

import com.imcode.tools.jdbmtosql.entities.superclasses.AbstractIdentity;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * Created by ruslan on 17.01.17.
 */
@Entity
public class DatabasesInfo extends AbstractIdentity<Long> implements Serializable {

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private HdbmDatabasesDescription hdbmDatabasesDescription;

    @Column
    private Long browseValue;

    public HdbmDatabasesDescription getHdbmDatabasesDescription() {
        return hdbmDatabasesDescription;
    }

    public void setHdbmDatabasesDescription(HdbmDatabasesDescription hdbmDatabasesDescription) {
        this.hdbmDatabasesDescription = hdbmDatabasesDescription;
    }

    public Long getBrowseValue() {
        return browseValue;
    }

    public void setBrowseValue(Long browseValue) {
        this.browseValue = browseValue;
    }
}
