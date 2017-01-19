package com.imcode.tools.jdbmtosql.transfer.services.schedulerhelpers;

import com.imcode.tools.jdbmtosql.transfer.services.abstractimpl.AbstractSchedulerHelper;
import com.imcode.tools.jdbmtosql.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

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

    @Override
    public Long save(Object target) throws Exception {
        Class<?> mappedEntityClass = target.getClass();
        Object repositoryFor = repositories.getRepositoryFor(mappedEntityClass);
        Method method = repositoryFor.getClass().getMethod(Constants.SAVE_METHOD_NAME, mappedEntityClass);
        ReflectionUtils.invokeMethod(method, target);
        return (Long) mappedEntityClass.getField(Constants.KEY_OF_MODIFIED).get(target);
    }
}
