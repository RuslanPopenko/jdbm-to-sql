package com.imcode.tools.jdbmtosql.transfer.interfaces;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import jdbm.btree.BTree;

import java.util.List;

/**
 * Created by ruslan on 17.01.17.
 */
public interface SchedulerWorker {
    SchedulerHelper getSchedulerHelper();
    HdbmDatabasesDescription getDatabaseDescription();
    BTree getDatabase();
    void process(List<String> entitiesJson, DatabasesInfo dbInfo, Long timestamp) throws Exception;
}
