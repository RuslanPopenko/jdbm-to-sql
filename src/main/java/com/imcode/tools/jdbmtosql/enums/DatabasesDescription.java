package com.imcode.tools.jdbmtosql.enums;

/**
 * Created by ruslan on 17.01.17.
 */
public enum DatabasesDescription {

    DATA("DataPath"), EVENTS("EventStorePath");

    private String propertyKey;

    private DatabasesDescription(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getPropertyKey() {
        return propertyKey;
    }

}
