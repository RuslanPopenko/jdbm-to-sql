package com.imcode.tools.jdbmtosql.entities.superclasses;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by ruslan on 19.01.17.
 */
@MappedSuperclass
public class AbstractStatedEntity<ID extends Serializable> extends AbstractIdentity<ID> implements StatedEntity, Serializable {

    public AbstractStatedEntity(Long modified, String streamflowType) {
        this.modified = modified;
        this.streamflowType = streamflowType;
    }

    public AbstractStatedEntity(ID identity, Long modified, String streamflowType) {
        super(identity);
        this.modified = modified;
        this.streamflowType = streamflowType;
    }

    @Column
    protected Long modified;

    @Column
    protected String streamflowType;

    public Long getModified() {
        return modified;
    }

    public void setModified(Long modified) {
        this.modified = modified;
    }

    public String getStreamflowType() {
        return streamflowType;
    }

    public void setStreamflowType(String streamflowType) {
        this.streamflowType = streamflowType;
    }

}
