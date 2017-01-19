package com.imcode.tools.jdbmtosql.transfer.services.schedulers;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.transfer.interfaces.EntityMapper;
import com.imcode.tools.jdbmtosql.transfer.interfaces.SchedulerHelper;
import com.imcode.tools.jdbmtosql.transfer.services.abstractimpl.AbstractSchedulerWorker;
import com.imcode.tools.jdbmtosql.transfer.services.schedulehelpers.DataSchedulerHelper;
import jdbm.btree.BTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ruslan on 17.01.17.
 */
@Component
public class DataSchedulerWorker extends AbstractSchedulerWorker {

    private final DataSchedulerHelper dataSchedulerHelper;
    private final BTree dataDb;
    private final EntityMapper dataEntityMapper;

    @Autowired
    public DataSchedulerWorker(DataSchedulerHelper dataSchedulerHelper,
                                 @Qualifier("dataDb") BTree dataDb,
                                 @Qualifier("dataEntityMapper") EntityMapper dataEntityMapper) {
        this.dataSchedulerHelper = dataSchedulerHelper;
        this.dataDb = dataDb;
        this.dataEntityMapper = dataEntityMapper;
    }

    @Override
    public SchedulerHelper getSchedulerHelper() {
        return dataSchedulerHelper;
    }

    @Override
    public HdbmDatabasesDescription getDatabaseDescription() {
        return HdbmDatabasesDescription.DATA;
    }

    @Override
    public BTree getDatabase() {
        return dataDb;
    }

    @Override
    public void process(List<String> entitiesJson, DatabasesInfo dbInfo, Long timestamp) throws Exception {

        for (String entityJson : entitiesJson) {
            Object mappedEntity =  dataEntityMapper.map(entityJson);

        }

    }
}
