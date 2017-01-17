package com.imcode.tools.jdbmtosql.tranfer.services.entitymappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imcode.tools.jdbmtosql.tranfer.interfaces.EntityMapper;
import com.imcode.tools.jdbmtosql.utils.Constants;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by ruslan on 17.01.17.
 */
@Service
public class EventsEntityMapper implements EntityMapper {

    private final ObjectMapper _objectMapper = new ObjectMapper();

    public Object map(String json) throws IOException {
       return _objectMapper.readValue(json, Constants.EVENTS_MAP_CLASS);
    }
}
