package com.imcode.tools.jdbmtosql.utils;

import com.imcode.tools.jdbmtosql.entities.TransactionDomainEvents;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruslan on 17.01.17.
 */
public class Constants {

    public static final int MAX_OBJECTS_NUMBER_FOR_CYCLE = 100;

    public static final Charset ENCODING = StandardCharsets.UTF_8;

    public static final int MRU_CACHE_MAX_SIZE = 1000;
    public static final int PAGE_SIZE = 16;
    public static final String NAMED_OBJECT_VALUE = "index";

    public static final long SCHEDULING_FIXED_RATE = 100L;
    public static final long SCHEDULING_INITIAL_DELAY = 1000L;

    public static final String KEY_OF_OBJECT_PROPERTIES_KEY = "properties";
    public static final String KEY_OF_OBJECT_ASSOCIATIONS_KEY = "associations";
    public static final String KEY_OF_OBJECT_MANY_ASSOCIATIONS_KEY = "manyassociations";
    public static final String KEY_OF_ENTITY_TYPE = "type";
    public static final String KEY_OF_MODIFIED = "modified";
    public static final String KEY_OF_STREAMFLOW_TYPE = "streamflowType";
    public static final String ID_PROPERTY_NAME = "identity";

    public static final String SAVE_METHOD_NAME = "save";

    public static final Class EVENTS_MAP_CLASS = TransactionDomainEvents.class;
    public static final Map<String, Class> DATA_MAP_CLASSES = Collections.unmodifiableMap(dataMapClasses());

    private static Map<String, Class> dataMapClasses() {
        HashMap<String, Class> dataMapClasses = new HashMap<>();
        return dataMapClasses;
    }
}
