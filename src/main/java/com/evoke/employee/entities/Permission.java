package com.evoke.employee.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import com.evoke.employee.audit.Auditable;

@Entity
@Table(name = "permission")
@SQLDelete(sql = "UPDATE permission SET deleted = true WHERE pid=?")
@Where(clause = "deleted=false")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@javax.persistence.Cacheable
public class Permission extends Auditable<String> {

    @Id
    @Column(name = "pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private boolean deleted = Boolean.FALSE;


    public Permission() {

    }

    public Permission(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    @Override
    public String toString() {
        return "Permission [id=" + id + ", name=" + name + "]";
    }


}
