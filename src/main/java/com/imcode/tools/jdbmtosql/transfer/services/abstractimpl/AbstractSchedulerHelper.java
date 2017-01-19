package com.imcode.tools.jdbmtosql.transfer.services.abstractimpl;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.repositories.DatabasesInfoRepository;
import com.imcode.tools.jdbmtosql.transfer.interfaces.SchedulerHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * Created by ruslan on 18.01.17.
 */
public abstract class AbstractSchedulerHelper implements SchedulerHelper {

    @Autowired
    private DatabasesInfoRepository databasesInfoRepository;

    @Override
    public final DatabasesInfo findBy(HdbmDatabasesDescription description) {
        return databasesInfoRepository.findByHdbmDatabasesDescription(description);
    }

    @Transactional
    public final void save(Object target, DatabasesInfo databasesInfo) throws Exception {
        Long timestamp = save(target);
        databasesInfo.setLastProcessedTimestamp(timestamp);
        databasesInfoRepository.save(databasesInfo);
    }
}
