package com.imcode.tools.jdbmtosql.tranfer.services.schedulers;

import com.imcode.tools.jdbmtosql.tranfer.interfaces.SchedulerWorker;
import com.imcode.tools.jdbmtosql.utils.Constants;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ruslan on 17.01.17.
 */
@Component
public class DataSchedulerWorker implements SchedulerWorker {

    @Override
    @Scheduled(fixedRate = Constants.SCHEDULING_FIXED_RATE, initialDelay = Constants.SCHEDULING_INITIAL_DELAY)
    public void scheduleWork() throws Exception {
        // TODO: 17.01.17 create this scheduleWork
    }

}
