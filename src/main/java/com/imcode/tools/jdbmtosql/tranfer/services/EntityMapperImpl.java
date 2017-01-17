package com.imcode.tools.jdbmtosql.tranfer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imcode.tools.jdbmtosql.enums.DatabaseDescription;
import com.imcode.tools.jdbmtosql.tranfer.interfaces.EntityMapper;
import com.imcode.tools.jdbmtosql.utils.Constants;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by ruslan on 17.01.17.
 */
@Service
public class EntityMapperImpl implements EntityMapper {

    private final ObjectMapper _objectMapper = new ObjectMapper();

    public Object map(String json, DatabaseDescription databaseDescription) throws IOException {
        final JSONObject jsonObject = new JSONObject(json);

        final Class mapClass;
        if (databaseDescription.equals(DatabaseDescription.EVENTS)) {
            mapClass = Constants.EVENTS_MAP_CLASS;
        } else {
            mapClass = getDataMapClass(jsonObject.getString(Constants.KEY_OF_ENTITY_TYPE));
        }

       return _objectMapper.readValue(json, mapClass);
    }

    private Class getDataMapClass(String className) {
        return Constants.DATA_MAP_CLASSES.get(className);
    }

}
