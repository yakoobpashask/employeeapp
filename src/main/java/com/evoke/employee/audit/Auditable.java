package com.evoke.employee.audit;

import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> {

    @CreatedBy
    @JsonIgnore
    protected T performedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @JsonIgnore
    protected Date performedOn;

    @LastModifiedBy
    @JsonIgnore
    protected T lastModifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @JsonIgnore
    protected Date lastModifiedDate;

    public T getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(T performedBy) {
        this.performedBy = performedBy;
    }

    public Date getPerformedOn() {
        return performedOn;
    }

    public void setPerformedOn(Date performedOn) {
        this.performedOn = performedOn;
    }

    public T getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(T lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
