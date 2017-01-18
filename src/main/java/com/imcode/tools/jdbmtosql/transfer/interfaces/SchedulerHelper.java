package com.imcode.tools.jdbmtosql.transfer.interfaces;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;

/**
 * Created by ruslan on 18.01.17.
 */
public interface SchedulerHelper {

    DatabasesInfo findBy(HdbmDatabasesDescription description);

}
