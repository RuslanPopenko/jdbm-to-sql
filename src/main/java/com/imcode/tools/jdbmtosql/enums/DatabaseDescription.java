package com.imcode.tools.jdbmtosql.enums;

import com.imcode.tools.jdbmtosql.entities.TransactionDomainEvents;

/**
 * Created by ruslan on 17.01.17.
 */
public enum DatabaseDescription {

    DATA("DataPath"), EVENTS("EventStorePath");

    private String propertyKey;

    private DatabaseDescription(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getPropertyKey() {
        return propertyKey;
    }

}
