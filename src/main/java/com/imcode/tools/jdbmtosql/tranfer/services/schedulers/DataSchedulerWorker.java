package com.imcode.tools.jdbmtosql.tranfer.services.schedulers;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.tranfer.interfaces.EntityMapper;
import com.imcode.tools.jdbmtosql.tranfer.interfaces.SchedulerWorker;
import com.imcode.tools.jdbmtosql.tranfer.services.schedulehelpers.DataSchedulerHelper;
import com.imcode.tools.jdbmtosql.utils.Constants;
import jdbm.btree.BTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ruslan on 17.01.17.
 */
@Component
public class DataSchedulerWorker implements SchedulerWorker {

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
    @Scheduled(fixedRate = Constants.SCHEDULING_FIXED_RATE, initialDelay = Constants.SCHEDULING_INITIAL_DELAY)
    public void scheduleWork() throws Exception {

    }

}
