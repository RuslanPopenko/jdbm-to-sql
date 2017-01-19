package com.imcode.tools.jdbmtosql.transfer.services.schedulers;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.transfer.interfaces.EntityMapper;
import com.imcode.tools.jdbmtosql.transfer.interfaces.SchedulerHelper;
import com.imcode.tools.jdbmtosql.transfer.services.abstractimpl.AbstractSchedulerWorker;
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

    private final EntityMapper dataEntityMapper;

    @Autowired
    public DataSchedulerWorker(@Qualifier("dataSchedulerHelper") SchedulerHelper dataSchedulerHelper,
                               @Qualifier("dataDb") BTree dataDb,
                               @Qualifier("dataEntityMapper") EntityMapper dataEntityMapper) {
        super(HdbmDatabasesDescription.DATA, dataDb, dataSchedulerHelper);
        this.dataEntityMapper = dataEntityMapper;
    }

    @Override
    public void process(List<String> entitiesJson, DatabasesInfo dbInfo) throws Exception {

        for (String entityJson : entitiesJson) {
            Object mappedEntity =  dataEntityMapper.map(entityJson);
            super.schedulerHelper.save(mappedEntity, dbInfo);
        }

    }
}
