package com.imcode.tools.jdbmtosql.transfer.services.schedulehelpers;

import com.imcode.tools.jdbmtosql.transfer.services.abstractimpl.AbstractSchedulerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 18.01.17.
 */
@Service
public class DataSchedulerHelper extends AbstractSchedulerHelper {

    private final ApplicationContext applicationContext;

    @Autowired
    public DataSchedulerHelper(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    

}
