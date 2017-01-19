package com.imcode.tools.jdbmtosql.transfer.services.schedulers;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.entities.TransactionDomainEvents;
import com.imcode.tools.jdbmtosql.enums.HdbmDatabasesDescription;
import com.imcode.tools.jdbmtosql.transfer.interfaces.EntityMapper;
import com.imcode.tools.jdbmtosql.transfer.interfaces.SchedulerHelper;
import com.imcode.tools.jdbmtosql.transfer.services.abstractimpl.AbstractSchedulerWorker;
import com.imcode.tools.jdbmtosql.transfer.services.schedulerhelpers.EventsSchedulerHelper;
import com.imcode.tools.jdbmtosql.utils.Constants;
import jdbm.btree.BTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ruslan on 17.01.17.
 */
@Component
public class EventsSchedulerWorker extends AbstractSchedulerWorker {

    private final SchedulerHelper eventsSchedulerHelper;
    private final EntityMapper eventsEntityMapper;

    @Autowired
    public EventsSchedulerWorker(@Qualifier("eventsSchedulerHelper") SchedulerHelper eventsSchedulerHelper,
                                 @Qualifier("eventsDb") BTree eventsDb,
                                 @Qualifier("eventsEntityMapper") EntityMapper eventsEntityMapper) {
        super(HdbmDatabasesDescription.EVENTS, eventsDb, eventsSchedulerHelper);
        this.eventsSchedulerHelper = eventsSchedulerHelper;
        this.eventsEntityMapper = eventsEntityMapper;
    }

    @Override
    public void process(List<String> entitiesJson, DatabasesInfo dbInfo) throws Exception {
        List<TransactionDomainEvents> result = new LinkedList<>();
        for (String transactionalDomainEventsJson : entitiesJson) {
            Object mappedEntity = eventsEntityMapper.map(transactionalDomainEventsJson);
            Assert.isTrue(mappedEntity.getClass().equals(Constants.EVENTS_MAP_CLASS), "Mapped entity isn't instance of " + Constants.EVENTS_MAP_CLASS);
            TransactionDomainEvents entity = (TransactionDomainEvents) mappedEntity;
            result.add(entity);
        }

        eventsSchedulerHelper.save(result, dbInfo);
    }
}
