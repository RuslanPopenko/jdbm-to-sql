package com.imcode.tools.jdbmtosql.tranfer.abstractimpl;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.repositories.DatabasesInfoRepository;
import com.imcode.tools.jdbmtosql.tranfer.interfaces.SchedulerHelper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ruslan on 18.01.17.
 */
public abstract class AbstractSchedulerHelper implements SchedulerHelper {

    @Autowired
    protected DatabasesInfoRepository databasesInfoRepository;

    @Override
    public DatabasesInfo findBy(HdbmDatabasesDescription description) {
        return databasesInfoRepository.findByHdbmDatabasesDescription(description);
    }

}
