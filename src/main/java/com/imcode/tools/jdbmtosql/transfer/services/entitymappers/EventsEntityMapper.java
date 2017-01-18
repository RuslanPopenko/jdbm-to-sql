package com.imcode.tools.jdbmtosql.transfer.services.entitymappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imcode.tools.jdbmtosql.transfer.interfaces.EntityMapper;
import com.imcode.tools.jdbmtosql.utils.Constants;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 17.01.17.
 */
@Service("eventsEntityMapper")
public class EventsEntityMapper implements EntityMapper {

    private final ObjectMapper _objectMapper = new ObjectMapper();

    public Object map(String json) throws Exception {
        return _objectMapper.readValue(json, Constants.EVENTS_MAP_CLASS);
    }
}
