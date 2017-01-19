package com.imcode.tools.jdbmtosql.transfer.services.schedulers;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.transfer.interfaces.EntityMapper;
import com.imcode.tools.jdbmtosql.transfer.interfaces.SchedulerHelper;
import com.imcode.tools.jdbmtosql.transfer.services.abstractimpl.AbstractSchedulerWorker;
import com.imcode.tools.jdbmtosql.transfer.services.schedulerhelpers.DataSchedulerHelper;
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
    private final EntityMapper dataEntityMapper;

    @Autowired
    public DataSchedulerWorker(DataSchedulerHelper dataSchedulerHelper,
                                 @Qualifier("dataDb") BTree dataDb,
                                 @Qualifier("dataEntityMapper") EntityMapper dataEntityMapper) {
        super(HdbmDatabasesDescription.DATA, dataDb, dataSchedulerHelper);
        this.dataSchedulerHelper = dataSchedulerHelper;
        this.dataEntityMapper = dataEntityMapper;
    }

    @Override
    public void process(List<String> entitiesJson, DatabasesInfo dbInfo) throws Exception {

        for (String entityJson : entitiesJson) {
            Object mappedEntity =  dataEntityMapper.map(entityJson);
            dataSchedulerHelper.save(mappedEntity, dbInfo);
        }

    }
}
