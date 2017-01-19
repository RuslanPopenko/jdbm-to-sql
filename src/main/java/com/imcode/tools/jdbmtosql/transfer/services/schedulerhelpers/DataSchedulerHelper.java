package com.imcode.tools.jdbmtosql.transfer.services.schedulerhelpers;

import com.imcode.tools.jdbmtosql.entities.DatabasesInfo;
import com.imcode.tools.jdbmtosql.transfer.services.abstractimpl.AbstractSchedulerHelper;
import com.imcode.tools.jdbmtosql.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Method;

/**
 * Created by ruslan on 18.01.17.
 */
@Service
public class DataSchedulerHelper extends AbstractSchedulerHelper {

    private final Repositories repositories;

    @Autowired
    public DataSchedulerHelper(ApplicationContext applicationContext) {
        repositories = new Repositories(applicationContext);
    }

    @Transactional
    public void save(Object mappedEntity, DatabasesInfo dbInfo) throws Exception {
        Class<?> mappedEntityClass = mappedEntity.getClass();
        Object repositoryFor = repositories.getRepositoryFor(mappedEntityClass);
        Method method = repositoryFor.getClass().getMethod(Constants.SAVE_METHOD_NAME, mappedEntityClass);
        ReflectionUtils.invokeMethod(method, mappedEntity);
        Long modified = (Long) mappedEntityClass.getField(Constants.KEY_OF_MODIFIED).get(mappedEntity);
        dbInfo.setLastProcessedTimestamp(modified);
        super.databasesInfoRepository.save(dbInfo);
    }
}
