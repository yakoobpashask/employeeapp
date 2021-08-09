package com.evoke.employee.audit;

import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class HistoryAuditable<String> {

    @CreatedBy
    @JsonIgnore
    protected String performedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @JsonIgnore
    protected Date performedOn;

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public Date getPerformedOn() {
        return performedOn;
    }

    public void setPerformedOn(Date performedOn) {
        this.performedOn = performedOn;
    }


}
