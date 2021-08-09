package com.evoke.employee.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.evoke.employee.audit.HistoryAuditable;

@Entity
@Table(name = "hrms_history")
public class HrmsHistory extends HistoryAuditable<String> {

    @Id
    @Column(name = "history_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer history_id;
    private String event;
    private String comments;

    public HrmsHistory() {

    }

    public HrmsHistory(String event, String comments) {
        super();
        this.event = event;
        this.comments = comments;
    }

    public Integer getHistory_id() {
        return history_id;
    }

    public void setHistory_id(Integer history_id) {
        this.history_id = history_id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
