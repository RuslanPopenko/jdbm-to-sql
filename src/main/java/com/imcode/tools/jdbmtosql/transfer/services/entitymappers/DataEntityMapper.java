package com.imcode.tools.jdbmtosql.transfer.services.entitymappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imcode.tools.jdbmtosql.transfer.interfaces.EntityMapper;
import com.imcode.tools.jdbmtosql.utils.Constants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by ruslan on 17.01.17.
 */
@Service("dataEntityMapper")
public class DataEntityMapper implements EntityMapper {

    private final ObjectMapper _objectMapper = new ObjectMapper();

    @Override
    public Object map(String json) throws Exception {
        JSONObject jsonObject = new JSONObject(json);

        JSONObject baseObject = jsonObject.getJSONObject(Constants.KEY_OF_OBJECT_PROPERTIES_KEY);

        addAssociations(baseObject, jsonObject);
        addManyAssociations(baseObject, jsonObject);

        String typeName = jsonObject.getString(Constants.KEY_OF_ENTITY_TYPE);

        Class mapClass = Constants.DATA_MAP_CLASSES.get(typeName);

        return _objectMapper.readValue(baseObject.toString(), mapClass);
    }

    private void addAssociations(JSONObject baseObject, JSONObject jsonObject) {
        JSONObject associations = jsonObject.getJSONObject(Constants.KEY_OF_OBJECT_ASSOCIATIONS_KEY);
        Set<String> keySet = associations.keySet();
        for (String propName : keySet) {
            baseObject.put(propName, wrapValue(jsonObject.getString(propName)));
        }
    }

    private void addManyAssociations(JSONObject baseObject, JSONObject jsonObject) {
        JSONObject manyAssociations = jsonObject.getJSONObject(Constants.KEY_OF_OBJECT_MANY_ASSOCIATIONS_KEY);
        Set<String> keySet = manyAssociations.keySet();
        for (String propName : keySet) {
            JSONArray jsonArrayOfIds = jsonObject.getJSONArray(propName);
            JSONArray result = new JSONArray();
            for (int i = 0; i < jsonArrayOfIds.length(); i++) {
                result.put(wrapValue(jsonArrayOfIds.getString(i)));
            }
            baseObject.put(propName, result);
        }
    }

    private JSONObject wrapValue(String id) {
        return new JSONObject().put(Constants.ID_PROPERTY_NAME, id);
    }

}
