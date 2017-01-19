package com.imcode.tools.jdbmtosql.transfer.services.abstractimpl;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.transfer.interfaces.SchedulerHelper;
import com.imcode.tools.jdbmtosql.transfer.interfaces.ScheduledWorker;
import com.imcode.tools.jdbmtosql.utils.Constants;
import jdbm.btree.BTree;
import jdbm.helper.Tuple;
import jdbm.helper.TupleBrowser;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.LinkedList;
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

    public abstract void process(List<String> entitiesJson, DatabasesInfo dbInfo) throws Exception;
    public abstract Object wrapBrowseValue(DatabasesInfo dbInfo);

    @Scheduled(fixedRate = Constants.SCHEDULING_FIXED_RATE, initialDelay = Constants.SCHEDULING_INITIAL_DELAY)
    public final void scheduledWork() throws Exception {
        DatabasesInfo dbInfo = schedulerHelper.findBy(databaseDescription);

        Object browseValue;
        if (dbInfo == null) {
            dbInfo = new DatabasesInfo();
            dbInfo.setHdbmDatabasesDescription(databaseDescription);
            browseValue = initialBrowseValue;
        } else {
            browseValue = wrapBrowseValue(dbInfo);
        }

        List<String> entitiesJson =
                getJsonDatabaseRecords(browseValue);

        process(entitiesJson, dbInfo);
    }

    private List<String> getJsonDatabaseRecords(Object browseValue) throws IOException {

        final TupleBrowser browser = database.browse(browseValue);

        Tuple tuple = new Tuple();

        List<String> jsonDatabaseRecords = new LinkedList<>();

        for (int i = 0; (i < Constants.MAX_OBJECTS_NUMBER_FOR_CYCLE) && browser.getNext(tuple); i++) {
            byte[] jsonData = (byte[]) tuple.getValue();
            jsonDatabaseRecords.add(new String(jsonData, Constants.ENCODING));
        }

        return jsonDatabaseRecords;
    }


}
