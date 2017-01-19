package com.imcode.tools.jdbmtosql.transfer.services.abstractimpl;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.transfer.interfaces.SchedulerHelper;
import com.imcode.tools.jdbmtosql.transfer.interfaces.SchedulerWorker;
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
public abstract class AbstractSchedulerWorker implements SchedulerWorker {

    @Scheduled(fixedRate = Constants.SCHEDULING_FIXED_RATE, initialDelay = Constants.SCHEDULING_INITIAL_DELAY)
    public final void scheduledWork() throws Exception {
        DatabasesInfo dbInfo = getSchedulerHelper().findBy(getDatabaseDescription());

        Long timestamp;
        if (dbInfo == null) {
            dbInfo = new DatabasesInfo();
            dbInfo.setHdbmDatabasesDescription(getDatabaseDescription());
            timestamp = 0L;
        } else {
            timestamp = dbInfo.getLastProcessedTimestamp() + 1;
        }

        List<String> entitiesJson =
                getJsonDatabaseRecords(getDatabase(), timestamp);

        process(entitiesJson, dbInfo, timestamp);
    }

    private List<String> getJsonDatabaseRecords(BTree db, Long timestamp) throws IOException {

        final TupleBrowser browser = db.browse(timestamp);

        Tuple tuple = new Tuple();

        List<String> jsonDatabaseRecords = new LinkedList<>();

        for (int i = 0; (i < Constants.MAX_OBJECTS_NUMBER_FOR_CYCLE) && browser.getNext(tuple); i++) {
            byte[] jsonData = (byte[]) tuple.getValue();
            jsonDatabaseRecords.add(new String(jsonData, Constants.ENCODING));
        }

        return jsonDatabaseRecords;
    }

    public abstract SchedulerHelper getSchedulerHelper();
    public abstract HdbmDatabasesDescription getDatabaseDescription();
    public abstract BTree getDatabase();
    public abstract void process(List<String> entitiesJson, DatabasesInfo dbInfo, Long timestamp) throws Exception;

}
