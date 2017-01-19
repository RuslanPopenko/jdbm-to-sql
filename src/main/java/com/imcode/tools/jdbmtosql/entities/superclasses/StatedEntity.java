package com.imcode.tools.jdbmtosql.entities.superclasses;

import java.io.Serializable;

/**
 * Created by ruslan on 19.01.17.
 */
public interface StatedEntity extends Serializable {

    Long getModified();

    void setModified(Long modified);

    String getStreamflowType();

    void setStreamflowType(String streamflowType);

}
