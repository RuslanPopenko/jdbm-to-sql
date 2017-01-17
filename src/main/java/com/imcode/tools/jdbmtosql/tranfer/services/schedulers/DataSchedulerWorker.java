package com.imcode.tools.jdbmtosql.tranfer.services.schedulers;

import com.imcode.tools.jdbmtosql.tranfer.interfaces.SchedulerWorker;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ruslan on 17.01.17.
 */
@Component
public class DataSchedulerWorker implements SchedulerWorker {

    @Override
    @Scheduled(fixedRate = 100, initialDelay = 1000)
    public void scheduleWork() {
        // TODO: 17.01.17 create this scheduleWork
    }

}
