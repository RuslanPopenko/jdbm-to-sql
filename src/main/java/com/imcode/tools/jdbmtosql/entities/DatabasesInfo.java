package com.imcode.tools.jdbmtosql.entities;

import com.imcode.tools.jdbmtosql.entities.superclasses.LongIdEntity;
import com.imcode.tools.jdbmtosql.enums.DatabaseDescription;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * Created by ruslan on 17.01.17.
 */
@Entity
public class DatabasesInfo extends LongIdEntity implements Serializable {

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private DatabaseDescription databaseDescription;

    @Column
    private Long lastProcessedTimestamp;

    public DatabaseDescription getDatabaseDescription() {
        return databaseDescription;
    }

    public void setDatabaseDescription(DatabaseDescription databaseDescription) {
        this.databaseDescription = databaseDescription;
    }

    public Long getLastProcessedTimestamp() {
        return lastProcessedTimestamp;
    }

    public void setLastProcessedTimestamp(Long lastProcessedTimestamp) {
        this.lastProcessedTimestamp = lastProcessedTimestamp;
    }
}
