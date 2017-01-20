package com.imcode.tools.jdbmtosql.transfer.services.abstractimpl;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.transfer.interfaces.SchedulerHelper;
import com.imcode.tools.jdbmtosql.transfer.interfaces.ScheduledWorker;
import com.imcode.tools.jdbmtosql.utils.Constants;
import jdbm.btree.BTree;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by ruslan on 18.01.17.
 */
public abstract class AbstractScheduledWorker implements ScheduledWorker {

    protected final HdbmDatabasesDescription databaseDescription;
    protected final BTree database;
    protected final SchedulerHelper schedulerHelper;
    protected final Object initialBrowseValue;

    public AbstractScheduledWorker(HdbmDatabasesDescription databaseDescription,
                                   BTree database,
                                   SchedulerHelper schedulerHelper,
                                   Object initialBrowseValue) {
        this.databaseDescription = databaseDescription;
        this.database = database;
        this.schedulerHelper = schedulerHelper;
        this.initialBrowseValue = initialBrowseValue;
    }

    protected abstract void process(List<String> entitiesJson, DatabasesInfo dbInfo) throws Exception;
    protected abstract Object getBrowseValue(DatabasesInfo dbInfo);
    protected abstract List<String> getJsonDatabaseRecords(Object browseValue) throws Exception;

    @Scheduled(fixedRate = Constants.SCHEDULING_FIXED_RATE, initialDelay = Constants.SCHEDULING_INITIAL_DELAY)
    public final void scheduledWork() throws Exception {
        DatabasesInfo dbInfo = schedulerHelper.findBy(databaseDescription);

        Object browseValue;
        if (dbInfo == null) {
            dbInfo = new DatabasesInfo();
            dbInfo.setHdbmDatabasesDescription(databaseDescription);
            browseValue = initialBrowseValue;
        } else {
            browseValue = getBrowseValue(dbInfo);
        }

        List<String> entitiesJson =
                getJsonDatabaseRecords(browseValue);

        process(entitiesJson, dbInfo);
    }


}
