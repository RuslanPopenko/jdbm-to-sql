package com.imcode.tools.jdbmtosql.entities;

import com.imcode.tools.jdbmtosql.entities.superclasses.AbstractIdentity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ruslan on 16.01.17.
 */
@Entity
public class ApplicationEvent extends AbstractIdentity<String> implements Serializable {

    @Column
    private String usecase;

    @Column
    private String name;

    @Column
    private String entity;

    @Column(name = "on_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date on;

    @Column(name = "by_user")
    private String by;

    @Column(columnDefinition = "text")
    private String parameters;

    @Column
    private String entityType;

    @Column
    private String version;

    public String getUsecase() {
        return usecase;
    }

    public void setUsecase(String usecase) {
        this.usecase = usecase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Date getOn() {
        return on;
    }

    public void setOn(Date on) {
        this.on = on;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
